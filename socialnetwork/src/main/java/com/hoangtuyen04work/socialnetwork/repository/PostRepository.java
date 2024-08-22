package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {

    @Query(value = "SELECT * FROM post WHERE content LIKE %:finded% OR title LIKE %:finded% ORDER BY created_at DESC LIMIT 50 OFFSET :page", nativeQuery = true)
    List<PostEntity> findByTitleContainingOrContentContaining(@Param("finded") String finded, @Param("page") Long offset);

    void deleteAllByUser(UserEntity user);
    Set<PostEntity> findAllByUser(UserEntity user);
    @Query("SELECT p.user FROM PostEntity p WHERE p.id = :postId")
    UserEntity findUserByPostId(@Param("postId") String postId);

    @Query("SELECT p.id FROM PostEntity p WHERE p.user = :user ORDER BY p.createdAt DESC ")
    List<String> getAllIdByUser(@Param("user") UserEntity user);


}
