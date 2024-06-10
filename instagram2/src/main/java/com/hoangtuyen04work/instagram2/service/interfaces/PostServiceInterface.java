package com.hoangtuyen04work.instagram2.service.interfaces;

import com.hoangtuyen04work.instagram2.dto.request.PostRequest;
import com.hoangtuyen04work.instagram2.dto.response.PostResponse;
import com.hoangtuyen04work.instagram2.entity.PostEntity;
import com.hoangtuyen04work.instagram2.exception.AppException;

import java.util.Set;

public interface PostServiceInterface {
    public Set<PostResponse> getAllPosts() throws AppException;
    public PostEntity findPostById(String postId) throws AppException;
    public PostResponse getPostResponseById(String postId) throws AppException;
    public PostResponse createPost(PostRequest postRequest);
    public PostResponse updatePost(String postId, PostRequest postRequest) throws AppException;
    public void deletePost(String postId) throws AppException;
    public boolean existsPost(String postId) throws AppException;
}


