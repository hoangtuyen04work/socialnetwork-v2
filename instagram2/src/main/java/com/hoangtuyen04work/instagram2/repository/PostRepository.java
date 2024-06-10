package com.hoangtuyen04work.instagram2.repository;

import com.hoangtuyen04work.instagram2.entity.PostEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {

    public void deleteAllByUserId(String userId);
    public Set<PostEntity> findAllByUserId(String userId);
}
