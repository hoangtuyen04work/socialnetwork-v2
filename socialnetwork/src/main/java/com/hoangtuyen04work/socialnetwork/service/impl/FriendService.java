package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.entity.FriendEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.repository.FriendRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.FriendServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class FriendService implements FriendServiceInterface {
    UserService userService;
    FriendRepository friendRepository;

     @Override
    public long countFriend(String id) throws AppException {
        UserEntity user = userService.findById(id);
        return user.getFriends().size();
    }

    @Override
    public boolean unFriend(String userId, String friendId) throws AppException {
        UserEntity user = userService.findById(userId);
        UserEntity friend = userService.findById(friendId);
        if (!friendRepository.existsByUserAndFriend(user, friend)) {
            FriendEntity friendEntity = new FriendEntity();
            friendEntity.setUser(user);
            friendEntity.setFriend(friend);
            friendRepository.delete(friendEntity);
            friendEntity = new FriendEntity();
            friendEntity.setUser(friend);
            friendEntity.setFriend(user);
            friendRepository.delete(friendEntity);
        }
        return true;
    }

    @Override
    public boolean addFriend(String userId, String friendId) throws AppException {
         UserEntity user = userService.findById(userId);
         UserEntity friend = userService.findById(friendId);
        if (!friendRepository.existsByUserAndFriend(user, friend)) {
            FriendEntity friendEntity = new FriendEntity();
            friendEntity.setUser(user);
            friendEntity.setFriend(friend);
            friendRepository.save(friendEntity);
            friendEntity = new FriendEntity();
            friendEntity.setUser(friend);
            friendEntity.setFriend(user);
            friendRepository.save(friendEntity);
        }
        return true;
    }

    @Override
    public boolean isFriend(String userId, String friendId) throws AppException {
        UserEntity user = userService.findById(userId);
        UserEntity friend = userService.findById(friendId);
        return friendRepository.existsByUserAndFriend(user, friend);
    }
}
