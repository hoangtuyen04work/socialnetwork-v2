package com.hoangtuyen04work.socialnetwork.controller;


import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.UserResponse;
import com.hoangtuyen04work.socialnetwork.entity.FriendEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.FriendService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
public class FriendController {
    FriendService friendService;

    @GetMapping("/friend/{id}/{offset}")
    public ApiResponse<List<UserResponse>> getFriends(@PathVariable String id, @PathVariable Long offset) throws AppException {
        return ApiResponse.<List<UserResponse>>builder()
                .message(NoticeResponse.success)
                .data(friendService.getFriends(id, offset))
                .build();
    }

    @GetMapping("/friend/check/{id1}/{id2}")
    public ApiResponse<Boolean> getFriend(@PathVariable String id1, @PathVariable String id2) throws AppException {
        return ApiResponse.<Boolean>builder()
                .message(NoticeResponse.success)
                .data(friendService.isFriend(id1, id2))
                .build();
    }

    @GetMapping("/friend/count/{id}")
    public ApiResponse<Long> getFriendsCount(@PathVariable String id) throws AppException {
        return ApiResponse.<Long>builder()
                .data(friendService.countFriend(id))
                .message(NoticeResponse.success)
                .build();
    }

    @DeleteMapping("/friend/{id}")
    public ApiResponse<Boolean> deleteFriend(@PathVariable String id) throws AppException {
        return ApiResponse.<Boolean>builder()
                .data(friendService.unFriend(id))
                .build();
    }

    @PostMapping("/friend/{id}")
    public ApiResponse<Boolean> addFriend(@PathVariable String id) throws AppException {
        return ApiResponse.<Boolean>builder()
                .data(friendService.addFriend(id))
                .message(NoticeResponse.success)
                .build();
    }
}
