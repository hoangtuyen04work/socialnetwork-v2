package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.response.IdResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.ShortenProfile;
import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.exception.ErrorCode;
import com.hoangtuyen04work.socialnetwork.mapper.PostMapper;
import com.hoangtuyen04work.socialnetwork.mapper.UserMapper;
import com.hoangtuyen04work.socialnetwork.service.interfaces.FindServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class FindService implements FindServiceInterface {
    PostMapper postMapper;
    UserMapper userMapper;
    UserService userService;
    PostService postService;

    @Override
    public List<String> findUser(String finded, Long page) throws AppException {
        List<UserEntity> userList = userService.find(finded, page*10 - 10);
        if(userList.isEmpty()){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        List<String> shortenProfileList = new ArrayList<>();
        for (UserEntity user : userList) {
            shortenProfileList.add(user.getId());
        }
        return shortenProfileList;
    }
    @Override
    public List<String> findPost(String finded, Long page) throws AppException {
        List<PostEntity> postEntityList = postService.find(finded, page*10 - 10);
        if(postEntityList.isEmpty()){
            throw new AppException(ErrorCode.POST_NOT_EXISTED);
        }
        List<String> postResponseList = new ArrayList<>();
        for (PostEntity post : postEntityList) {
            postResponseList.add(post.getId());
        }
        return postResponseList;
    }
}
