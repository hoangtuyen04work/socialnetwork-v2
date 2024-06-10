package com.hoangtuyen04work.instagram2.service.impl;


import com.hoangtuyen04work.instagram2.dto.request.LogoutRequest;
import com.hoangtuyen04work.instagram2.dto.request.RefreshTokenRequest;
import com.hoangtuyen04work.instagram2.dto.request.UserRequest;
import com.hoangtuyen04work.instagram2.dto.response.TokenResponse;
import com.hoangtuyen04work.instagram2.entity.InvalidatedTokenEntity;
import com.hoangtuyen04work.instagram2.entity.RoleEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.exception.ErrorCode;
import com.hoangtuyen04work.instagram2.mapper.UserMapper;
import com.hoangtuyen04work.instagram2.service.interfaces.AuthenticationServiceInterface;
import com.hoangtuyen04work.instagram2.utils.TokenUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Service
public class AuthenticationService implements AuthenticationServiceInterface {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private InvalidatedTokenService invalidatedTokenService;

    @Autowired
    private TokenUtils tokenUtils;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws AppException, ParseException, JOSEException {
        SignedJWT signedJWT = tokenUtils.verifyToken(refreshTokenRequest.getToken());
        var signToken = tokenUtils.verifyToken(refreshTokenRequest.getToken());
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
        InvalidatedTokenEntity invalidatedToken =
                InvalidatedTokenEntity.builder().id(jit).expiryTime(expiryTime).build();
        invalidatedTokenService.save(invalidatedToken);
        var userId = signedJWT.getJWTClaimsSet().getSubject();
        var user = userService.findByUserId(userId);
        var token = tokenUtils.refreshToken(user);
        return TokenResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    @Override
    public TokenResponse logout(LogoutRequest logoutRequest) throws AppException, ParseException, JOSEException {
        String token = logoutRequest.getToken();
        var signToken = tokenUtils.verifyToken(logoutRequest.getToken());
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
        InvalidatedTokenEntity invalidatedToken =
                InvalidatedTokenEntity.builder().id(jit).expiryTime(expiryTime).build();
        invalidatedTokenService.save(invalidatedToken);
        return null;
    }

    public TokenResponse getUser(UserRequest userRequest) throws AppException, JOSEException {
        UserEntity userEntity = userMapper.toUserEntity(userRequest);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        var user = userService.findByUserId(userRequest.getUserId());
        if( user == null || ! passwordEncoder.matches(userRequest.getPassword(), user.getPassword())){
            throw new AppException(ErrorCode.WRONG_PASSWORD_OR_USERNAME);
        }
        String token = tokenUtils.generateToken(user);
        return TokenResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }


    public TokenResponse createUser(UserRequest userRequest) throws JOSEException, AppException {
        if (userRequest == null ||
                userRequest.getPassword() == null || userRequest.getPassword().isBlank() ||
                userRequest.getUserId() == null || userRequest.getUserId().isBlank() ||
                userRequest.getUserName() == null || userRequest.getUserName().isBlank()) {
            throw new AppException(ErrorCode.INVALID_INPUT);
        }
        UserEntity userEntity = userMapper.toUserEntity(userRequest);
        if(userService.existsUser(userRequest.getUserId())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        RoleEntity roleEntity = roleService.findRole("USER");
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleEntity);
        userEntity.setRoles(roles);
        var user = userService.createUser(userEntity);
        String token = tokenUtils.generateToken(user);
        return TokenResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public TokenResponse updateUser(UserRequest userRequest) throws AppException, JOSEException {
        if(userRequest.getUserId() != SecurityContextHolder.getContext().getAuthentication().getName()){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        UserEntity userEntity = userService.updateUser(userRequest);
        String token =  tokenUtils.generateToken(userEntity);
        return TokenResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


}
