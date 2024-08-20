package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.response.UserResponse;
import com.hoangtuyen04work.socialnetwork.entity.FriendEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.mapper.UserMapper;
import com.hoangtuyen04work.socialnetwork.repository.FriendRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.FriendServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class FriendService implements FriendServiceInterface {
    UserService userService;
    FriendRepository friendRepository;
    UserMapper userMapper;
    @Override
    public List<UserResponse> getFriends(String id, Long offset) throws AppException {
        List<FriendEntity> friendsEntity = friendRepository.findFriends(id , offset*15 - 15);
        List<UserResponse> friends = new ArrayList<>();
        while(!friendsEntity.isEmpty()) {
            UserEntity user = (friendsEntity.get(0).getFriend());
            friends.add(userMapper.toUserResponse(user));
            friendsEntity.remove(0);
        }
        return friends;
    }

     @Override
    public long countFriend(String id) throws AppException {
        UserEntity user = userService.findById(id);
        return user.getFriends().size();
    }

    @Override
    public boolean unFriend(String id) throws AppException {
        UserEntity user = userService.getUserInHolder();
        UserEntity friend = userService.findById(id);
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
    public boolean addFriend(String id) throws AppException {
         UserEntity user = userService.getUserInHolder();
         UserEntity friend = userService.findById(id);
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
