package com.hoangtuyen04work.instagram2.controller;


import com.hoangtuyen04work.instagram2.dto.request.PostRequest;
import com.hoangtuyen04work.instagram2.dto.response.ApiResponse;
import com.hoangtuyen04work.instagram2.dto.response.PostResponse;
import com.hoangtuyen04work.instagram2.dto.response.TokenResponse;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.service.impl.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ApiResponse<Set<PostResponse>> getAllPosts() throws AppException {
        return ApiResponse.<Set<PostResponse>>builder()
                .message("Success")
                .data(postService.getAllPosts())
                .build();
    }

    @PostMapping("/newpost")
    public ApiResponse<PostResponse> newPost(@RequestBody PostRequest postRequest) {
        return ApiResponse.<PostResponse>builder()
                .message("created new post successfully")
                .data(postService.createPost(postRequest))
                .build();
    }

    @GetMapping("/post/{postId}")
    public ApiResponse<PostResponse> getPost(@PathVariable String postId) throws AppException {
        return ApiResponse.<PostResponse>builder()
                .message("get post successfully")
                .data(postService.getPostResponseById(postId))
                .build();
    }

    @PutMapping("/post/edit/{postId}")
    public ApiResponse<TokenResponse> editPost(@PathVariable String postId, @RequestBody PostRequest postRequest) throws AppException {
        postService.updatePost(postId, postRequest);
        return ApiResponse.<TokenResponse>builder()
                .message("edited post successfully")
                .data(null)
                .build();
    }

    @DeleteMapping("/post/delete/{postId}")
    public ApiResponse<TokenResponse> deletePost(@PathVariable String postId) throws AppException {
        postService.deletePost(postId);
        return ApiResponse.<TokenResponse>builder()
                .message("deleted post successfully")
                .data(null)
                .build();
    }
}
