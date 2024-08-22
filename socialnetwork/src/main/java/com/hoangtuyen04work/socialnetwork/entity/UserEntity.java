package com.hoangtuyen04work.socialnetwork.entity;


import com.hoangtuyen04work.socialnetwork.constant.State;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Builder
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;


    @Column
    String imageUrl;

    @Column
    String email;

    @Column(nullable = false, unique = true)
    String userId;

    @Column(nullable = false)
    String userName;

    @Column(nullable = false)
    String password;

    LocalDate DOB;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updateAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_users")
    Set<RoleEntity> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    Set<PostEntity> posts = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    Set<CommentEntity> comments = new HashSet<>();

    @OneToMany(mappedBy = "friend", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    List<FriendEntity> friends = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    List<FriendEntity> user_friend = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    List<FollowEntity> user_follow = new ArrayList<>();
    @OneToMany(mappedBy = "following", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    List<FollowEntity> following = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    List<MessageEntity> sender = new ArrayList<>();
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    List<MessageEntity> receiver = new ArrayList<>();
}