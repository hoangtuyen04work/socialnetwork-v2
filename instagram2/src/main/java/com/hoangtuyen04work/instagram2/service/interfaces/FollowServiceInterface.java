package com.hoangtuyen04work.instagram2.service.interfaces;

import com.hoangtuyen04work.instagram2.dto.request.FollowRequest;

public interface FollowServiceInterface {
    public boolean addAndRemoveFollow(String followerId);
    public boolean isFollowing(String followerId, String followingId);
    public long countFollowers(String followerId);
    public long countFollowings(String followingId);
}
