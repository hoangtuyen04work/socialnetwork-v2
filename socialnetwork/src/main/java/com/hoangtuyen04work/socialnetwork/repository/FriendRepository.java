package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.FriendEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<FriendEntity, String> {
    @Query("SELECT COUNT(f) > 0 FROM FriendEntity f WHERE f.user = :user AND f.friend = :friend")
    boolean existsByUserAndFriend(@Param("user") UserEntity user, @Param("friend") UserEntity friend);
}
