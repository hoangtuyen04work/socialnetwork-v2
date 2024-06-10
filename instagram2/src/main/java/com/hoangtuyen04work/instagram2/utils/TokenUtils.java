package com.hoangtuyen04work.instagram2.utils;

import com.hoangtuyen04work.instagram2.entity.RoleEntity;
import com.hoangtuyen04work.instagram2.entity.UserEntity;
import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.exception.ErrorCode;
import com.hoangtuyen04work.instagram2.repository.InvalidatedTokenRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;

@Component
public class TokenUtils {

    @Value("${jwt.signerKey}")
    private  String SIGNER_KEY;

    @Autowired
    InvalidatedTokenRepository invalidatedToken;

    public boolean introspect(String token) throws AppException, ParseException, JOSEException {
        boolean isValid = true;
        verifyToken(token);
        return isValid;
    }

    public  String generateToken(UserEntity userEntity) throws JOSEException{
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        String roleScope = buildRole(userEntity.getRoles());
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userEntity.getUserId())
                .issuer("hoangtuyen04work.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS)
                                .toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("name", userEntity.getUserName())
                .claim("scope", roleScope)
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        return jwsObject.serialize();
    }

    public  String refreshToken(UserEntity userEntity) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        String roleScope = buildRole(userEntity.getRoles());
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userEntity.getUserId())
                .issuer("hoangtuyen04work.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(100, ChronoUnit.HOURS)
                                .toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("name", userEntity.getUserName())
                .claim("scope", roleScope)
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        return jwsObject.serialize();
    }

    //build role String for jwt claimset because scope is String type not list
    public  String buildRole(Set<RoleEntity> roleEntities){
        StringJoiner joiner = new StringJoiner(" ");
        for(RoleEntity roleEntity : roleEntities){
            joiner.add("ROLE_" + roleEntity.getRole());
        }
        return joiner.toString();
    }


    // verify that this valid token, no time limit, no
    public SignedJWT verifyToken(String token) throws JOSEException, ParseException, AppException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY);
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(verifier);
        if(!(verified && expiryTime.after(new Date()))){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        if (invalidatedToken.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new AppException(ErrorCode.NOT_AUTHENTICATED);
        }
        return signedJWT;
    }
}
