package com.hoangtuyen04work.instagram2.service.interfaces;

import com.hoangtuyen04work.instagram2.dto.request.UserRequest;
import com.hoangtuyen04work.instagram2.dto.response.UserResponse;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import com.hoangtuyen04work.instagram2.exception.AppException;

public interface UserServiceInterface {
    public UserResponse getUserByUserId(String userId);
    public UserEntity findByUserId(String userId);
    public UserEntity createUser(UserEntity userEntity);
    public UserEntity updateUser(UserRequest userRequest) throws AppException;
    public void deleteUser(String userId) throws AppException;
    public boolean existsUser(String userId);
}
