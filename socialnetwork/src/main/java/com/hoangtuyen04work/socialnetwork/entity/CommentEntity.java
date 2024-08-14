package com.hoangtuyen04work.socialnetwork.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // Enable JPA Auditing for this entity
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(columnDefinition = "TEXT")
    String content;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    PostEntity post;
    @ManyToOne
    @JoinColumn(nullable = false)
    UserEntity user;
}
