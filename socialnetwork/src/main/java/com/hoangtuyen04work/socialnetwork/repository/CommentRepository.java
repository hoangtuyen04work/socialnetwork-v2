package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.CommentEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    void deleteAllByUserId(String userId);

    @Query("SELECT c FROM CommentEntity c WHERE c.post.id = :postId")
    Set<CommentEntity> getAllByPostId(@Param("postId") String postId);

    @Query("SELECT count(c) FROM CommentEntity c WHERE c.post.id = :postId")
    Long countComment(@Param("postId") String id);

    @Query("SELECT c.user FROM CommentEntity c WHERE c.id = :commentId")
    UserEntity findUserByCommentId(@Param("commentId") String id);

}
