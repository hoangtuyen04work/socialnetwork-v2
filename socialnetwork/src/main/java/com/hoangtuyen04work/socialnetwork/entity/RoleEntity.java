package com.hoangtuyen04work.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "role", nullable = false, unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles")
    Set<UserEntity> users;

}
