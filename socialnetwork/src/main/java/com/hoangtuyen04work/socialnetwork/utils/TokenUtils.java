package com.hoangtuyen04work.socialnetwork.utils;

import com.hoangtuyen04work.socialnetwork.entity.RoleEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.exception.ErrorCode;
import com.hoangtuyen04work.socialnetwork.repository.InvalidatedTokenRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenUtils {

    @Value("${jwt.signerKey}")
    String SIGNER_KEY;

    @Autowired
    InvalidatedTokenRepository invalidatedToken;

    public boolean introspect(String token) throws AppException, ParseException, JOSEException {
        verifyToken(token);
        return true;
    }

    public  String generateToken(UserEntity userEntity) throws JOSEException{
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = generateClamSet(userEntity, 1000L);
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        return jwsObject.serialize();
    }

    public  String refreshToken(UserEntity userEntity) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = generateClamSet(userEntity, 100L);
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        return jwsObject.serialize();
    }

    private JWTClaimsSet generateClamSet(UserEntity userEntity, Long time){
        String roleScope = buildRole(userEntity.getRoles());
        return new JWTClaimsSet.Builder()
                .subject(userEntity.getId())
                .issuer("hoangtuyen.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(time, ChronoUnit.HOURS)
                                .toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", roleScope)
                .build();
    }

    public  String buildRole(Set<RoleEntity> roleEntities){
        StringJoiner joiner = new StringJoiner(" ");
        for(RoleEntity roleEntity : roleEntities){
            joiner.add("ROLE_" + roleEntity.getRole());
        }
        return joiner.toString();
    }

    public SignedJWT verifyToken(String token) throws  AppException {
        try{
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY);
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expiryTime =   signedJWT.getJWTClaimsSet().getExpirationTime();
            boolean verified = signedJWT.verify(verifier);
            if(!(verified && expiryTime.after(new Date()))){
                throw new AppException(ErrorCode.NOT_AUTHENTICATED);
            }
            if (invalidatedToken.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
                throw new AppException(ErrorCode.NOT_AUTHENTICATED);
            }
            else{
                return signedJWT;
            }
        } catch (Exception e){
            throw  new AppException(ErrorCode.TOKEN_INVALID);
        }
    }
}
