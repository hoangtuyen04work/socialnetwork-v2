package com.hoangtuyen04work.instagram2.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class) // Enable JPA Auditing for this entity

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "userid", nullable = false, unique = true)
    private String userId;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "dateofbirth")
    private String DOB;

    @Column(name = "createddate", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "modifieddate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "deleteddate")
    private LocalDateTime deletedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('active', 'deleted') DEFAULT 'active'")
    private State state = State.active;

    public enum State {
        active,
        deleted
    }

    @ManyToMany
    @JoinTable(
            name = "role_users",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    Set<PostEntity> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    Set<CommentEntity> comments;

    @OneToMany(mappedBy = "follower",  cascade = CascadeType.REMOVE)
    Set<FollowEntity> followers;

    @OneToMany(mappedBy = "follower",  cascade = CascadeType.REMOVE)
    Set<FollowEntity> followees;

}
