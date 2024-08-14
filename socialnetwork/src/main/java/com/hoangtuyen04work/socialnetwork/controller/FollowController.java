package com.hoangtuyen04work.socialnetwork.controller;

import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.request.FollowRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.IdRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.FollowService;
import com.hoangtuyen04work.socialnetwork.service.impl.FriendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {

    private final FriendService friendService;
    FollowService followService;

    public FollowController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/followed")
    public ApiResponse<Boolean> isFollowing(@RequestBody FollowRequest followRequest) throws AppException {
        return ApiResponse.<Boolean>builder()
                .data(followService.isFollowing(followRequest))
                .message(NoticeResponse.success)
                .build();
    }

    @GetMapping("/follow")
    public ApiResponse<String> follow(@RequestBody IdRequest idRequest) throws AppException {
        followService.follow(idRequest);

        return ApiResponse.<String>builder()
                .message(NoticeResponse.success)
                .data(null)
                .build();
    }

    @GetMapping("/unfollow")
    public ApiResponse<String> unfollow(@RequestBody IdRequest idRequest) throws AppException {
        followService.unfollow(idRequest);
        return ApiResponse.<String>builder()
                .message(NoticeResponse.success)
                .data(null)
                .build();
    }

    @GetMapping("/count/follower")
    public ApiResponse<Long> countFollower(@RequestBody  IdRequest idRequest) throws AppException {
        return ApiResponse.<Long>builder()
                .message(NoticeResponse.success)
                .data(followService.countFollowers(idRequest))
                .build();
    }

    @GetMapping("/count/following")
    public ApiResponse<Long> countFollowing(@RequestBody  IdRequest idRequest) throws AppException {
        return ApiResponse.<Long>builder()
                .message(NoticeResponse.success)
                .data( followService.countFollowings(idRequest))
                .build();
    }

}
