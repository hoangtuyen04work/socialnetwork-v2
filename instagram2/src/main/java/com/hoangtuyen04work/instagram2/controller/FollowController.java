package com.hoangtuyen04work.instagram2.controller;


import com.hoangtuyen04work.instagram2.dto.request.FollowRequest;
import com.hoangtuyen04work.instagram2.dto.response.ApiResponse;
import com.hoangtuyen04work.instagram2.service.impl.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {
    @Autowired
    private FollowService followService;

    @GetMapping("/user/follow")
    public ApiResponse<String> follow(@RequestBody FollowRequest followRequest) {
        followService.addAndRemoveFollow(followRequest.getFollowingid());
        return ApiResponse.<String>builder()
                .message("Successfully followed user")
                .data(null)
                .build();
    }

    @GetMapping("/user/count/follower/{userId}")
    public ApiResponse<Long> countFollower(@PathVariable String userId) {
        return ApiResponse.<Long>builder()
                .message("Successfully counted followers")
                .data(followService.countFollowers(userId))
                .build();
    }

    @GetMapping("/user/count/following/{userId}")
    public ApiResponse<Long> countFollowing(@PathVariable String userId) {
        return ApiResponse.<Long>builder()
                .message("Successfully counted following")
                .data( followService.countFollowings(userId))
                .build();
    }
}