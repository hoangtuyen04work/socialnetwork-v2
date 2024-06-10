
package com.hoangtuyen04work.instagram2.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "follow")
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "followerid", nullable = false)
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "following", nullable = false)
    private UserEntity following;

}