package com.hoangtuyen04work.instagram2.controller;

import com.hoangtuyen04work.instagram2.dto.request.LogoutRequest;
import com.hoangtuyen04work.instagram2.dto.request.RefreshTokenRequest;
import com.hoangtuyen04work.instagram2.dto.request.UserRequest;
import com.hoangtuyen04work.instagram2.dto.response.ApiResponse;
import com.hoangtuyen04work.instagram2.dto.response.TokenResponse;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.service.impl.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ApiResponse<TokenResponse> signup(@RequestBody UserRequest userRequest) throws JOSEException, AppException {
        return ApiResponse
                .<TokenResponse>builder()
                .data(authenticationService.createUser(userRequest))
                .message("Sigup successful")
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody UserRequest userRequest) throws JOSEException, AppException {
        return ApiResponse
                .<TokenResponse>builder()
                .data(authenticationService.getUser(userRequest))
                .message("login successful")
                .build();
    }

    @PostMapping("/logoutuser")
    public ApiResponse<TokenResponse> test(@RequestBody LogoutRequest logoutRequest) throws AppException, ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<TokenResponse>builder()
                .message("logout successful")
                .data(null)
                .build();
    }

    @PostMapping("/refreshtoken")
    public ApiResponse<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws AppException, ParseException, JOSEException {
        return ApiResponse.<TokenResponse>builder()
                .data(authenticationService.refreshToken(refreshTokenRequest))
                .message("refresh successful")
                .build();
    }

}
