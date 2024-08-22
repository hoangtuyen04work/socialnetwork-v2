package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.UserResponse;
import com.hoangtuyen04work.socialnetwork.entity.RoleEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.exception.ErrorCode;
import com.hoangtuyen04work.socialnetwork.mapper.UserMapper;
import com.hoangtuyen04work.socialnetwork.repository.*;
import com.hoangtuyen04work.socialnetwork.service.Amazon3SService;
import com.hoangtuyen04work.socialnetwork.service.interfaces.UserServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class UserService implements UserServiceInterface {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleService roleService;
    Amazon3SService amazon3SService;

    @Override
    public List<UserEntity> find(String name, Long page) throws AppException {
        List<UserEntity> result = userRepository.findByUserIdContaining(name, page);
        if(result.isEmpty()){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        return result;
    }

    @Override
    public UserEntity save(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
    @Override
    public boolean isYourSelf(String id){
        return Objects.equals(id, getIdInHolder());
    }
    @Override
    public UserEntity findByUserId(String userId) throws AppException {
        return userRepository.findByUserId(userId).orElseThrow(
                ()-> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
    @Override
    public UserResponse editName(String id, UserRequest request) throws AppException {
        UserEntity userEntity = getUserInHolder();
        userEntity.setUserName(request.getUserName());
        return userMapper.toUserResponse(save(userEntity));
    }
    @Override
    public UserEntity findById(String id) throws AppException {
        return userRepository.findById(id).orElseThrow(()-> new AppException((ErrorCode.USER_EXISTED)));
    }
    @Override
    public void setRoleUser(UserEntity userEntity){
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleService.findRole("USER"));
        userEntity.setRoles(roles);
    }

    @Override
    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    @Override
    public UserResponse getByUserId(String userId) throws AppException {
        UserEntity userEntity = userRepository.findByUserId(userId).orElseThrow(
                ()->new AppException(ErrorCode.USER_NOT_EXISTED));
        return getInfo(userEntity.getId());
    }
    @Override
    public UserResponse getInfo(String id) throws AppException {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
                UserResponse response =  userMapper.toUserResponse(userEntity.get()).hideSensitiveInfo();
                response.setNumberFollower(countNumberFollower(id));
                System.err.println(countNumberFollower(id));
                response.setNumberFriend(countNumberFriend(id));
                System.err.println(countNumberFriend(id));
                return response;

        } else {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
    }

    @Override
    public Long countNumberFollower(String id){
        return userRepository.countNumberFollower(id);
    }

    @Override
    public Long countNumberFriend(String id){
        return userRepository.countNumberFriend(id);
    }



    @Override
    public UserEntity create(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

//    //chua lam
//    @PreAuthorize("authentication.name == userRequest.id")
//    public UserEntity updatePassword(UserRequest userRequest) throws AppException {
//        String id = getIdInHolder();
//        UserEntity userEntity = userRepository.findById(id)
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//        return userRepository.save(userEntity);
//    }

    @Override
    public void delete() throws AppException {
        UserEntity userEntity = userRepository.findById(getIdInHolder())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.delete(userEntity);
    }

    @Override
    public boolean existed(String userId) {
        return userRepository.existsByUserId(userId);
    }
    @Override
    public String getIdInHolder(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @Override
    public UserEntity getUserInHolder(){
        return userRepository.findById(getIdInHolder()).get();
    }
}
