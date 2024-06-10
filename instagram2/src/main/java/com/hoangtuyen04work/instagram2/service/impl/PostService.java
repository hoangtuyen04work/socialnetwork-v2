package com.hoangtuyen04work.instagram2.service.impl;


import com.hoangtuyen04work.instagram2.dto.request.PostRequest;
import com.hoangtuyen04work.instagram2.dto.response.PostResponse;
import com.hoangtuyen04work.instagram2.entity.PostEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.exception.ErrorCode;
import com.hoangtuyen04work.instagram2.mapper.PostMapper;
import com.hoangtuyen04work.instagram2.repository.PostRepository;
import com.hoangtuyen04work.instagram2.service.interfaces.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Service
public class PostService implements PostServiceInterface {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserService userService;


    @Override
    public Set<PostResponse> getAllPosts() throws AppException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userService.findByUserId(userId);
        Set<PostEntity> postEntities = postRepository.findAllByUserId(userEntity.getId());
        Set<PostResponse> postResponses = new HashSet<>();
        for (PostEntity postEntity : postEntities) {
            PostResponse postResponse = postMapper.toPostResponse(postEntity);
            postResponses.add(postResponse);
        }
        return postResponses;
    }

    @Override
    public PostEntity findPostById(String postId) throws AppException {
        return (postRepository.findById(postId).orElseThrow(() ->  new AppException(ErrorCode.POST_NOT_EXISTED)));
    }

    @Override
    public PostResponse getPostResponseById(String id) throws AppException {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() ->  new AppException(ErrorCode.POST_NOT_EXISTED));
        return postMapper.toPostResponse(postEntity);
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        PostEntity postEntity = postMapper.toPostEntity(postRequest);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        postEntity.setUser(userService.findByUserId(authentication.getName()));
        postRepository.save(postEntity);
        return postMapper.toPostResponse(postEntity);
    }

    @Override
    public PostResponse updatePost(String postId, PostRequest postRequest) throws AppException {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        UserEntity user = userService.findByUserId(postEntity.getUser().getId());
        if(!Objects.equals(user.getId(), postEntity.getUser().getId())) {
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        if(postRequest.getTitle() != null) {
            postEntity.setTitle(postRequest.getTitle());
        }
        if(postRequest.getContent() != null){
            postEntity.setContent(postRequest.getContent());
        }
        postRepository.save(postEntity);
        return postMapper.toPostResponse(postEntity);
    }

    @Override
    public void deletePost(String postId) throws AppException {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(()-> new AppException(ErrorCode.POST_NOT_FOUND));
        UserEntity user = userService.findByUserId(postEntity.getUser().getId());
        if(!Objects.equals(user.getId(), postEntity.getUser().getId())) {
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        postRepository.delete(postEntity);
    }

    @Override
    public boolean existsPost(String postId) throws AppException {
        return postRepository.existsById(postId);
    }
}
