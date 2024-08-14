package com.hoangtuyen04work.socialnetwork.controller;

import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.request.LogoutRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.RefreshTokenRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.AuthenticationResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.TokenResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ApiResponse<AuthenticationResponse> signup(@RequestBody UserRequest userRequest) throws JOSEException, AppException {
        return ApiResponse
                .<AuthenticationResponse>builder()
                .data(authenticationService.signup(userRequest))
                .message(NoticeResponse.success)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody UserRequest userRequest) throws JOSEException, AppException {
        return ApiResponse
                .<AuthenticationResponse>builder()
                .data(authenticationService.login(userRequest))
                .message(NoticeResponse.success)
                .build();
    }

    @PostMapping("/logoutnow")
    public ApiResponse<AuthenticationResponse> test(@RequestBody LogoutRequest logoutRequest) throws AppException, ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .message(NoticeResponse.success)
                .data(null)
                .build();
    }

    @PostMapping("/refreshtoken")
    public ApiResponse<TokenResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws AppException, ParseException, JOSEException {
        return ApiResponse.<TokenResponse>builder()
                .data(authenticationService.refreshToken(refreshTokenRequest))
                .message(NoticeResponse.success)
                .build();
    }

    @PostMapping("/login/google")
    public ApiResponse<AuthenticationResponse> loginGoogle(OAuth2User oAuth2User) throws JOSEException {
        return ApiResponse.<AuthenticationResponse>builder()
                .data(authenticationService.loginGoogle(oAuth2User))
                .message(NoticeResponse.success)
                .build();
    }

}
