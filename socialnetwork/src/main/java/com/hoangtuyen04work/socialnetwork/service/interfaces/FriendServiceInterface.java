package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

public interface FriendServiceInterface {
    long countFriend(String id) throws AppException;


    boolean unFriend(String userId, String friendId) throws AppException;

    boolean addFriend(String userId, String friendId) throws AppException;

    boolean isFriend(String userId, String friendId) throws AppException;
}
