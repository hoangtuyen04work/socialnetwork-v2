package com.hoangtuyen04work.instagram2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class) // Enable JPA Auditing for this entity

public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @CreatedDate

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createddate;

    @LastModifiedDate
    @Column( columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime modifieddate;

    @Column
    private LocalDateTime deleteddate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('active', 'deleted') DEFAULT 'active'")
    @Builder.Default
    private State state = State.active;

    public enum State {
        active,
        deleted
    }

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    UserEntity user;

    @OneToMany(mappedBy = "post",  cascade = CascadeType.REMOVE)
    Set<CommentEntity> commentEntities;

    @OneToMany(mappedBy = "post",  cascade = CascadeType.REMOVE)
    Set<PostLikeEntity> postLikeEntities;

}
