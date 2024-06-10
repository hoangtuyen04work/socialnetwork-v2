package com.hoangtuyen04work.instagram2.service.interfaces;

import com.hoangtuyen04work.instagram2.dto.request.LogoutRequest;
import com.hoangtuyen04work.instagram2.dto.request.RefreshTokenRequest;
import com.hoangtuyen04work.instagram2.dto.request.UserRequest;
import com.hoangtuyen04work.instagram2.dto.response.TokenResponse;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationServiceInterface {
    public TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws AppException, ParseException, JOSEException;
    public TokenResponse logout(LogoutRequest logoutRequest) throws AppException, ParseException, JOSEException;
    public TokenResponse getUser(UserRequest userRequest) throws JOSEException, AppException;
    public TokenResponse createUser(UserRequest userRequest) throws JOSEException, AppException;
    public TokenResponse updateUser(UserRequest userRequest) throws JOSEException, AppException;

}
