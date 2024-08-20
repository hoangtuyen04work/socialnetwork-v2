package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
import com.hoangtuyen04work.socialnetwork.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikeEntity, String> {
    void delete(PostLikeEntity postLikeEntity);
    @Query("select count(p) from PostLikeEntity p where p.post = :post")
    long countLikedByPostId(@Param("post")PostEntity postEntity);
    boolean existsByPostIdAndUserId(String postId, String userId);
}
