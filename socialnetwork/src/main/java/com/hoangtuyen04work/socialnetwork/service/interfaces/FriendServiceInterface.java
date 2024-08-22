package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.response.UserResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

import java.util.List;

public interface FriendServiceInterface {
    List<UserResponse> getFriends(String id, Long offset) throws AppException;

    List<String> getFriendsId(String id);

    long countFriend(String id) throws AppException;


    boolean unFriend(String id) throws AppException;

    boolean addFriend(String id) throws AppException;

    boolean isFriend(String userId, String friendId) throws AppException;
}
