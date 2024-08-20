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
    public long countFollowings(String id) throws AppException {
        return followRepository.countFollowing(userService.findById(id));
    }
    @Override
    public long countFollowers(String id) throws AppException {
        return followRepository.countFollower(userService.findById(id));
    }
    @Override
    public void follow( String id) throws AppException {
        FollowEntity follow = new FollowEntity();
        follow.setUser(userService.getUserInHolder());
        follow.setFollowing(userService.findById(id));
        followRepository.save(follow);
        if(isFollowing(id, userService.getIdInHolder())){
            friendService.addFriend(id);
        }
    }
    @Override
    public void unfollow(String id) throws AppException {
        if(friendService.isFriend(id, userService.getIdInHolder())){
            friendService.unFriend(id);
        }
        followRepository.unFollow(userService.getIdInHolder(), id);
    }
}
