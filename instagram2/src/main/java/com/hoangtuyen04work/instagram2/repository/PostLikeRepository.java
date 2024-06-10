package com.hoangtuyen04work.instagram2.repository;

import com.hoangtuyen04work.instagram2.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity, String> {
    public void deleteByPostIdAndUserId(String postId, String userId);
    public long countLikedByPostId(String postId);
    public boolean existsByPostIdAndUserId(String postId, String userId);
}
