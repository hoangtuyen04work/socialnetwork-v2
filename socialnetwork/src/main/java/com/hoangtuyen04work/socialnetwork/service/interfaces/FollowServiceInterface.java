package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.request.FollowRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.IdRequest;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

public interface FollowServiceInterface {

    boolean isFollowing(FollowRequest followRequest) throws AppException;



    boolean isFollowing(String userId, String followingId);

    long countFollowings(IdRequest idRequest) throws AppException;


    long countFollowers(IdRequest idRequest) throws AppException;

    void follow(IdRequest idRequest) throws AppException;

    void unfollow(IdRequest idRequest) throws AppException;
}
