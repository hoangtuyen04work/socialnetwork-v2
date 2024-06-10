package com.hoangtuyen04work.instagram2.controller;


import com.hoangtuyen04work.instagram2.dto.request.CommentRequest;
import com.hoangtuyen04work.instagram2.dto.response.ApiResponse;
import com.hoangtuyen04work.instagram2.dto.response.CommentResponse;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.service.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment/{postId}")
    public ApiResponse<CommentResponse> newComment(@PathVariable("postId") String postId, @RequestBody CommentRequest commentRequest) throws AppException {
        return ApiResponse.<CommentResponse>builder()
                .message("add comment successfully")
                .data(commentService.addComment(postId, commentRequest))
                .build();
    }

    @GetMapping("/comments/{postId}")
    public ApiResponse<Set<CommentResponse>> getAllComments(@PathVariable String postId) throws AppException {
        return ApiResponse.<Set<CommentResponse>>builder()
                .data(commentService.getAllComment(postId))
                .message("get all comment successfully")
                .build();
    }

    @PutMapping("/comment/{commentId}")
    public ApiResponse<CommentResponse> editComment(@PathVariable("commentId") String commentId, @RequestBody CommentRequest commentRequest) throws AppException {
        return ApiResponse.<CommentResponse>builder()
                .message("edit comment successfully")
                .data(commentService.updateComment(commentId, commentRequest))
                .build();
    }

    @DeleteMapping("/comment/{commentId}")
    public ApiResponse<String> deleteComment(@PathVariable("commentId") String commentId) throws AppException {
        commentService.deleteComment(commentId);
        return ApiResponse.<String>builder()
                .message("delete comment successfully")
                .data(null)
                .build();
    }

}
