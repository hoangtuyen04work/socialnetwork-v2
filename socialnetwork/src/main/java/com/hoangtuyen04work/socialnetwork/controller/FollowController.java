package com.hoangtuyen04work.socialnetwork.controller;

import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.request.FollowRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.IdRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.FollowService;
import com.hoangtuyen04work.socialnetwork.service.impl.FriendService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FollowController {

    FollowService followService;



    @GetMapping("/followed/{id}/{following}")
    public ApiResponse<Boolean> isFollowing(@PathVariable String id, @PathVariable String following) throws AppException {
        return ApiResponse.<Boolean>builder()
                .data(followService.isFollowing(id, following))
                .message(NoticeResponse.success)
                .build();
    }

    @PostMapping("/follow/{id}")
    public ApiResponse<String> follow(@PathVariable String id) throws AppException {
        followService.follow(id);

        return ApiResponse.<String>builder()
                .message(NoticeResponse.success)
                .data(null)
                .build();
    }

    @PostMapping("/follow/un/{id}")
    public ApiResponse<String> unfollow(@PathVariable String id) throws AppException {
        followService.unfollow(id);
        return ApiResponse.<String>builder()
                .message(NoticeResponse.success)
                .data(null)
                .build();
    }

    @GetMapping("/follow/countfollower/{id}")
    public ApiResponse<Long> countFollower(@PathVariable String id) throws AppException {
        return ApiResponse.<Long>builder()
                .message(NoticeResponse.success)
                .data(followService.countFollowers(id))
                .build();
    }

    @GetMapping("/follow/countfollowing/{id}")
    public ApiResponse<Long> countFollowing(@PathVariable String id) throws AppException {
        return ApiResponse.<Long>builder()
                .message(NoticeResponse.success)
                .data( followService.countFollowings(id))
                .build();
    }

}
