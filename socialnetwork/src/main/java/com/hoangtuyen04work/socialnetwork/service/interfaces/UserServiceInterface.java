package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.request.IdRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.UserResponse;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

import java.util.List;

public interface UserServiceInterface {
    boolean isYourSelf(String id);

    UserEntity findByUserId(String userId) throws AppException;

    UserResponse editName(String id, UserRequest request) throws AppException;

    UserEntity findById(String id) throws AppException;

    void setRoleUser(UserEntity userEntity);

    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);

    UserResponse getByUserId(String userId) throws AppException;

    UserResponse getInfo(String id) throws AppException;

    Long countNumberFollower(String id);

    Long countNumberFriend(String id);

    UserEntity create(UserEntity userEntity);

    void delete() throws AppException;

    boolean existed(String id);


    List<UserEntity> find(String name, Long page) throws AppException;

    UserEntity save(UserEntity userEntity) throws AppException;

    String getIdInHolder();

    UserEntity getUserInHolder();
}
