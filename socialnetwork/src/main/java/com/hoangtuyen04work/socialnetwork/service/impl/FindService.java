package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.ShortenProfile;
import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
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
    public List<ShortenProfile> findUser(String finded, Long page) throws AppException {
        List<UserEntity> userList = userService.find(finded, page*10 - 10);
        List<ShortenProfile> shortenProfileList = new ArrayList<>();
        for (UserEntity user : userList) {
            shortenProfileList.add(userMapper.toShortenProfile(user));
        }
        return shortenProfileList;
    }
    @Override
    public List<PostResponse> findPost(String finded, Long page) throws AppException {
            List<PostEntity> postEntityList = postService.find(finded, page*10 - 10);
        List<PostResponse> postResponseList = new ArrayList<>();
        for (PostEntity post : postEntityList) {
            postResponseList.add(postMapper.toPostResponse(post));
        }
        return postResponseList;
    }
}
