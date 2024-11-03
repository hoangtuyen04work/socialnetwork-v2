package com.hoangtuyen04work.socialnetwork.configuration;

import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.exception.ErrorCode;
import com.hoangtuyen04work.socialnetwork.utils.TokenUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;
import com.nimbusds.jose.JOSEException;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signerKey}")
    @NonFinal
    String signerKey;
    @NonFinal
    NimbusJwtDecoder nimbusJwtDecoder = null;
    TokenUtils tokenUtils;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            validateToken(token);
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HmacSHA512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                        .macAlgorithm(MacAlgorithm.HS512)
                        .build();
            return nimbusJwtDecoder.decode(token);
        } catch (Exception e) {
            try {
                throw new AppException(ErrorCode.TOKEN_INVALID);
            } catch (AppException ex) {
                System.err.println("Token Error");
            }
            return null;
        }
    }

    private void validateToken(String token) throws AppException, JOSEException, ParseException {
        boolean isValid = tokenUtils.introspect(token);
        if (!isValid) {
            throw new AppException(ErrorCode.TOKEN_INVALID);
        }
    }
}
