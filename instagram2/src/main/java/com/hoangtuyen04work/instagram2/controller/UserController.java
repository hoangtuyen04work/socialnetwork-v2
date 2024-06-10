package com.hoangtuyen04work.instagram2.controller;

import com.hoangtuyen04work.instagram2.dto.request.UserRequest;
import com.hoangtuyen04work.instagram2.dto.response.ApiResponse;
import com.hoangtuyen04work.instagram2.dto.response.TokenResponse;
import com.hoangtuyen04work.instagram2.dto.response.UserResponse;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.service.impl.AuthenticationService;
import com.hoangtuyen04work.instagram2.service.impl.UserService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;


    @GetMapping("/user/infor/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable String userId) {
        return ApiResponse.<UserResponse>builder()
                .data(userService.getUserByUserId(userId))
                .message("That is your info")
                .build();
    }

    @PutMapping("/user/edit")
    public ApiResponse<TokenResponse> editUser(@RequestBody UserRequest userRequest) throws AppException, JOSEException {
        return ApiResponse.<TokenResponse>builder()
                .data(authenticationService.updateUser(userRequest))
                .message("edit successfully")
                .build();
    }

    @DeleteMapping("/user/delete/{userId}")
    public ApiResponse<TokenResponse> deleteUser(@PathVariable String userId ) throws AppException{
        userService.deleteUser(userId);
        return ApiResponse.<TokenResponse>builder()
                .data(null)
                .message("delete successfully")
                .build();
    }
}
