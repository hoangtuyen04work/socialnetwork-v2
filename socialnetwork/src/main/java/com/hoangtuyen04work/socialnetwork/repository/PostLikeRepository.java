package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity, String> {
    void delete(PostLikeEntity postLikeEntity);
    long countLikedByPostId(String postId);
    boolean existsByPostIdAndUserId(String postId, String userId);
}
