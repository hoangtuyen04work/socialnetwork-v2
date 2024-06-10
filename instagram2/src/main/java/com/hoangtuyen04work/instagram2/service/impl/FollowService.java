package com.hoangtuyen04work.instagram2.service.impl;

import com.hoangtuyen04work.instagram2.dto.request.FollowRequest;
import com.hoangtuyen04work.instagram2.entity.FollowEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import com.hoangtuyen04work.instagram2.repository.FollowRepository;
import com.hoangtuyen04work.instagram2.repository.UserRepository;
import com.hoangtuyen04work.instagram2.service.interfaces.FollowServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class FollowService implements FollowServiceInterface {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowRepository followRepository;

    @Override
    public boolean addAndRemoveFollow(String followingId) {
        UserEntity following = userService.findByUserId(followingId);
        UserEntity follower = userService.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        FollowEntity follow = new FollowEntity();
        follow.setFollower(follower);
        follow.setFollowing(following);
        if(isFollowing(follower.getId(), following.getId())) {
            followRepository.deleteByFollowerIdAndFollowingId(follower.getId(), following.getId());
        }
        else{
            followRepository.save(follow);
        }
        return true;
    }

    @Override
    public boolean isFollowing(String followerId, String followingId) {

        if(followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)){
            return true;
        }
        return false;
    }

    @Override
    public long countFollowers(String followerId) {
        String id = userService.findByUserId(followerId).getId();
        return followRepository.countByFollowingId(id);
    }

    @Override
    public long countFollowings(String followingId) {
        String id = userService.findByUserId(followingId).getId();
        return followRepository.countByFollowerId(id);
    }
}
