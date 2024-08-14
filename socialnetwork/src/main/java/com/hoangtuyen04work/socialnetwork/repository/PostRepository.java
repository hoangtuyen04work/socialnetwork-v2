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

    @Query(value = "SELECT * FROM post  post WHERE post.title LIKE %:finded% OR post.content LIKE %:finded% ORDER BY post.createAt DESC LIMIT 10 OFFSET :page ", nativeQuery = true)
    List<PostEntity> findByTitleContainingOrContentContaining(@Param("finded") String finded, @Param("page") Long page);
    void deleteAllByUser(UserEntity user);
    Set<PostEntity> findAllByUser(UserEntity user);
}
