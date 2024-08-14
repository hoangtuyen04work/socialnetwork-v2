package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.FollowEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, String> {
    @Query("SELECT COUNT(f) FROM FollowEntity f WHERE f.following = :user")
    Long countFollower(@Param("user") UserEntity user);

    @Query("SELECT COUNT(f) FROM FollowEntity f WHERE f.user = :user")
    Long countFollowing(@Param("user") UserEntity user);


    boolean existsByUserIdAndFollowingId(String userId, String followingId);


}
