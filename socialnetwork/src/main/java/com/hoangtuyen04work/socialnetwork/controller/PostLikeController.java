package com.hoangtuyen04work.socialnetwork.controller;


import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.request.PostLikeRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.PostLikeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostLikeController {
    PostLikeService postLikeService;

    @GetMapping("/count/{id}")
    public ApiResponse<Long> countLikes(@PathVariable String id) throws AppException {
        return ApiResponse.<Long>builder()
                .data(postLikeService.countLikes(id))
                .message(NoticeResponse.success)
                .build();
    }


    @PutMapping
    public ApiResponse<String> likePost(@RequestBody PostLikeRequest postLikeRequest) throws AppException {
        postLikeService.addAndRemove(postLikeRequest);
        return ApiResponse.<String>builder()
                .data(null)
                .message(NoticeResponse.success)
                .build();
    }

    @GetMapping("/check/{id}")
    public ApiResponse<Boolean> checkLike(@PathVariable String id) throws AppException {
        return ApiResponse.<Boolean>builder()
                .data(postLikeService.isLiked(id))
                .message(NoticeResponse.success)
                .build();
    }

}
