package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.request.LogoutRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.RefreshTokenRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.AuthenticationResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.TokenResponse;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.nimbusds.jose.JOSEException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

public interface AuthenticationServiceInterface {
    @Transactional
    AuthenticationResponse loginGoogle(OAuth2User principal) throws  JOSEException;

    TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws AppException, ParseException, JOSEException;
    void logout(LogoutRequest logoutRequest) throws AppException, ParseException, JOSEException;
    AuthenticationResponse login(UserRequest userRequest) throws JOSEException, AppException;
    AuthenticationResponse signup(UserRequest userRequest) throws JOSEException, AppException;

    void setRoleUser(UserEntity userEntity);
}
