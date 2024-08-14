package com.hoangtuyen04work.socialnetwork.controller;


import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.request.NewPostRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.AuthenticationResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @GetMapping("/posts")
    public ApiResponse<Set<PostResponse>> getAllPost() throws AppException {
        return ApiResponse.<Set<PostResponse>>builder()
                .message("Success")
                .data(postService.getAll())
                .build();
    }

    @PostMapping("/post/new")
    public ApiResponse<PostResponse> newPost(@RequestBody NewPostRequest newPostRequest) throws AppException {
        return ApiResponse.<PostResponse>builder()
                .message(NoticeResponse.success)
                .data(postService.create(newPostRequest))
                .build();
    }

    @GetMapping("/post/{id}")
    public ApiResponse<PostResponse> getPost(@PathVariable String id) throws AppException {
        return ApiResponse.<PostResponse>builder()
                .message(NoticeResponse.success)
                .data(postService.getById(id))
                .build();
    }

    @PutMapping("/post/edit/{id}")
    public ApiResponse<AuthenticationResponse> editPost(@PathVariable String id, @RequestBody NewPostRequest newPostRequest) throws AppException {
        postService.update(id, newPostRequest);
        //return info post after edit
        return ApiResponse.<AuthenticationResponse>builder()
                .message(NoticeResponse.success)
                .data(null)
                .build();
    }

    @DeleteMapping("/post/delete/{id}")
    public ApiResponse<AuthenticationResponse> deletePost(@PathVariable String id) throws AppException {
        postService.delete(id);
        return ApiResponse.<AuthenticationResponse>builder()
                .message(NoticeResponse.success)
                .data(null)
                .build();
    }
}
