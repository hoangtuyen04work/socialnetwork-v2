package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.request.FollowRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.IdRequest;
import com.hoangtuyen04work.socialnetwork.entity.FollowEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.repository.FollowRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.FollowServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class FollowService implements FollowServiceInterface {
    UserService userService;
    FollowRepository followRepository;
    FriendService friendService;

    @Override
    public boolean isFollowing(FollowRequest followRequest) throws AppException {
        if(userService.existed(followRequest.getFollowingId()) && userService.existed(followRequest.getUserId())){
            return isFollowing(followRequest.getUserId(), followRequest.getFollowingId());
        }
        return false;
    }
    @Override
    public boolean isFollowing(String userId, String followingId){
        return followRepository.existsByUserIdAndFollowingId(userId, followingId);
    }
    @Override
    public long countFollowings(IdRequest idRequest) throws AppException {
        return followRepository.countFollowing(userService.findById(idRequest.getId()));
    }
    @Override
    public long countFollowers(IdRequest idRequest) throws AppException {
        return followRepository.countFollower(userService.findById(idRequest.getId()));
    }
    @Override
    public void follow(IdRequest idRequest) throws AppException {
        FollowEntity follow = new FollowEntity();
        follow.setUser(userService.getUserInHolder());
        follow.setFollowing(userService.findById(idRequest.getId()));
        followRepository.save(follow);
        if(isFollowing(idRequest.getId(), userService.getIdInHolder())){
            friendService.addFriend(userService.getIdInHolder(), idRequest.getId());
        }
    }
    @Override
    public void unfollow(IdRequest idRequest) throws AppException {
        FollowEntity follow = new FollowEntity();
        follow.setUser(userService.getUserInHolder());
        follow.setFollowing(userService.findById(idRequest.getId()));
        if(friendService.isFriend(idRequest.getId(), userService.getIdInHolder())){
            friendService.unFriend(userService.getIdInHolder(), idRequest.getId());
        }
        followRepository.delete(follow);
    }
}
