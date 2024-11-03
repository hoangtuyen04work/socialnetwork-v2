package com.hoangtuyen04work.socialnetwork.controller;

import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.request.IdRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.AuthenticationResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.UserResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping("/info/{id}")
    public ApiResponse<UserResponse> getInfo(@PathVariable String id) throws AppException {
        return ApiResponse.<UserResponse>builder()
                .message(NoticeResponse.success)
                .data(userService.getInfo(id))
                .build();
    }

    @PutMapping("/user/edit/[id]")
    public ApiResponse<UserResponse> editName(@PathVariable String id, @RequestBody UserRequest userRequest) throws AppException, JOSEException {
        return ApiResponse.<UserResponse>builder()
                .data(userService.editName(id, userRequest))
                .message(NoticeResponse.success)
                .build();
    }

    @DeleteMapping("/user/delete")
    public ApiResponse<AuthenticationResponse> deleteUser() throws AppException{
        userService.delete();
        return ApiResponse.<AuthenticationResponse>builder()
                .data(null)
                .message(NoticeResponse.success)
                .build();
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<UserResponse> findUserId(@PathVariable String userId) throws AppException {
        return ApiResponse.<UserResponse>builder()
                .message(NoticeResponse.success)
                .data(userService.getByUserId(userId))
                .build();
    }
}
