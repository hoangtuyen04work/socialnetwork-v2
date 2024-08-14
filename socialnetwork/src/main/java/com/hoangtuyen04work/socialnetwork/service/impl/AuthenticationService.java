package com.hoangtuyen04work.socialnetwork.service.impl;
import com.hoangtuyen04work.socialnetwork.dto.request.LogoutRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.RefreshTokenRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.AuthenticationResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.TokenResponse;
import com.hoangtuyen04work.socialnetwork.entity.InvalidatedTokenEntity;
import com.hoangtuyen04work.socialnetwork.entity.RoleEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.exception.ErrorCode;
import com.hoangtuyen04work.socialnetwork.mapper.UserMapper;
import com.hoangtuyen04work.socialnetwork.service.interfaces.AuthenticationServiceInterface;
import com.hoangtuyen04work.socialnetwork.utils.TokenUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class AuthenticationService implements AuthenticationServiceInterface {
    UserService userService;
    UserMapper userMapper;
    RoleService roleService;
    InvalidatedTokenService invalidatedTokenService;
    TokenUtils tokenUtils;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    @Transactional
    public AuthenticationResponse loginGoogle(OAuth2User principal) throws  JOSEException {
        UserEntity userEntity = new UserEntity();
        if(userService.existsByEmail(principal.getAttribute("email"))) {
            userEntity = userService.findByEmail(principal.getAttribute("email"));
        }
        else{
            userEntity.setEmail(principal.getAttribute("email"));
            userService.setRoleUser(userEntity);
            userEntity = userService.save(userEntity);
        }
        return AuthenticationResponse.builder()
                .info(userMapper.toUserResponse(userEntity))
                .token(tokenUtils.generateToken(userEntity))
                .build();
    }


    @Override
    public TokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws AppException, ParseException, JOSEException {
        SignedJWT signedJWT = tokenUtils.verifyToken(refreshTokenRequest.getToken());
        InvalidatedTokenEntity invalidatedToken = InvalidatedTokenEntity.builder()
                                                        .id(signedJWT.getJWTClaimsSet().getJWTID())
                                                        .expiryTime(signedJWT.getJWTClaimsSet().getExpirationTime())
                                                        .build();
        invalidatedTokenService.save(invalidatedToken);
        String id = signedJWT.getJWTClaimsSet().getSubject();
        UserEntity user = userService.findById(id);
        return TokenResponse.builder()
                .token(tokenUtils.refreshToken(user))
                .build();
    }

    @Override
    public void logout(LogoutRequest logoutRequest) throws AppException, ParseException, JOSEException {
        SignedJWT signToken = tokenUtils.verifyToken(logoutRequest.getToken());
        invalidatedTokenService.save(InvalidatedTokenEntity
                                            .builder()
                                            .id(signToken.getJWTClaimsSet().getJWTID())
                                            .expiryTime(signToken.getJWTClaimsSet().getExpirationTime())
                                            .build()
                                    );
    }
    @Override
    public AuthenticationResponse login(UserRequest userRequest) throws AppException, JOSEException {
        UserEntity user = userService.findByUserId(userRequest.getUserId());
        if( ! passwordEncoder.matches(userRequest.getPassword(), user.getPassword())){
            throw new AppException(ErrorCode.WRONG_PASSWORD_OR_USERNAME);
        }
        String token = tokenUtils.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .info(userMapper.toUserResponse(user))
                .build();
    }
    @Override
    public AuthenticationResponse signup(UserRequest userRequest) throws JOSEException, AppException {
        if(userService.existed(userRequest.getUserId())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        UserEntity userEntity = userMapper.toUserEntity(userRequest);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        setRoleUser(userEntity);
        userEntity = userService.create(userEntity);
        return AuthenticationResponse.builder()
                .token(tokenUtils.generateToken(userEntity))
                .info(userMapper.toUserResponse(userEntity))
                .build();
    }
    @Override
    public void setRoleUser(UserEntity userEntity){
        RoleEntity roleEntity = roleService.findRole("USER");
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleEntity);
        userEntity.setRoles(roles);
    }
}
