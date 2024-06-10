package com.hoangtuyen04work.instagram2.repository;

import com.hoangtuyen04work.instagram2.entity.FollowEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FollowRepository extends JpaRepository<FollowEntity, String> {
    public void deleteAllByFollowingId(String followingId);
    public void deleteAllByFollowerId(String followerId);
    public void deleteByFollowerIdAndFollowingId(String followerId, String followingId);
    public boolean existsByFollowerIdAndFollowingId(String followerId, String followingId);
    public long countByFollowingId(String followingId);
    public long countByFollowerId(String followerId);
}
