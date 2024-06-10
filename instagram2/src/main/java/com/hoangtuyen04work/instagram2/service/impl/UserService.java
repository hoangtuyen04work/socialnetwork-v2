package com.hoangtuyen04work.instagram2.service.impl;

import com.hoangtuyen04work.instagram2.dto.request.UserRequest;
import com.hoangtuyen04work.instagram2.dto.response.UserResponse;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.exception.ErrorCode;
import com.hoangtuyen04work.instagram2.mapper.UserMapper;
import com.hoangtuyen04work.instagram2.repository.*;
import com.hoangtuyen04work.instagram2.service.interfaces.UserServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    FollowRepository followRepository;

    @Override
    @PostAuthorize("returnObject.userId == authentication.name")
    public UserResponse getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return userMapper.toUserResponse(userEntity);
    }

    @Override
    public UserEntity findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public UserEntity createUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserRequest userRequest) throws AppException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        if(userRequest.getUserName() != null){
            userEntity.setUserName(userRequest.getUserName());
        }
        if(userRequest.getDOB() != null){
            userEntity.setDOB(String.valueOf(userRequest.getDOB()));
        }
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(String userId) throws AppException {
        if(!userRepository.existsByUserId(userId)){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        UserEntity userEntity = userRepository.findByUserId(userId);
        userRepository.delete(userEntity);
    }

    @Override
    public boolean existsUser(String userId) {
        return userRepository.existsByUserId(userId);
    }
}
