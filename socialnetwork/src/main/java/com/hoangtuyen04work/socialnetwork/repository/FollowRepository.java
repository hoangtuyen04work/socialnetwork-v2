package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.FollowEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, String> {
    @Query("SELECT COUNT(f) FROM FollowEntity f WHERE f.following = :user")
    Long countFollower(@Param("user") UserEntity user);

    @Query("SELECT COUNT(f) FROM FollowEntity f WHERE f.user = :user")
    Long countFollowing(@Param("user") UserEntity user);

    @Query("SELECT COUNT(f) > 0 FROM FollowEntity f WHERE f.user.id = :userId and f.following.id = :followingId")
    boolean existsByUserIdAndFollowingId(@Param("userId")String userId,@Param("followingId") String followingId);

    @Modifying
    @Transactional
    @Query("delete FROM FollowEntity f WHERE f.user.id = :userId and f.following.id = :followingId")
    void unFollow(@Param("userId")String userId,@Param("followingId") String followingId);
}
