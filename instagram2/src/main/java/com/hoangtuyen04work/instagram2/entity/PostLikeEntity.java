
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
@Table(name = "post_like")
public class PostLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postid", nullable = false)
    private PostEntity post;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userid", nullable = false)
    private UserEntity user;
}