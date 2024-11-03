package com.hoangtuyen04work.socialnetwork.controller;

import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.request.CommentRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.CommentResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
    CommentService commentService;

    @PostMapping("/new")
    public ApiResponse<CommentResponse> newComment(@RequestBody CommentRequest commentRequest) throws AppException {
        return ApiResponse.<CommentResponse>builder()
                .message(NoticeResponse.success)
                .data(commentService.add(commentRequest))
                .build();
    }

    @GetMapping("/all/{id}")
    public ApiResponse<Set<CommentResponse>> getAllComments(@PathVariable  String id) throws AppException {
        return ApiResponse.<Set<CommentResponse>>builder()
                .data(commentService.getAll(id))
                .message(NoticeResponse.success)
                .build();
    }

    @PutMapping()
    public ApiResponse<CommentResponse> edit(@RequestBody CommentRequest commentRequest) throws AppException {
        return ApiResponse.<CommentResponse>builder()
                .message(NoticeResponse.success)
                .data(commentService.update(commentRequest))
                .build();
    }

    @DeleteMapping()
    public ApiResponse<String> deleteComment(@RequestBody CommentRequest commentRequest) throws AppException {
        commentService.delete(commentRequest);
        return ApiResponse.<String>builder()
                .message(NoticeResponse.success)
                .data(null)
                .build();
    }
}
