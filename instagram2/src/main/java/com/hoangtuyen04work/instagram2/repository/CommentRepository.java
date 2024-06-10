package com.hoangtuyen04work.instagram2.repository;

import com.hoangtuyen04work.instagram2.entity.CommentEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    public void deleteAllByUserId(String userId);
    public Set<CommentEntity> getAllByPostId(String postId);
}
