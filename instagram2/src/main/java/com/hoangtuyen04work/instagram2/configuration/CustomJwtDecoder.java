package com.hoangtuyen04work.instagram2.configuration;

import com.hoangtuyen04work.instagram2.exception.AppException;
import com.hoangtuyen04work.instagram2.service.impl.AuthenticationService;
import com.hoangtuyen04work.instagram2.utils.TokenUtils;
import org.springframework.security.oauth2.jwt.JwtDecoder;


import java.text.ParseException;
import java.util.Objects;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;


import com.nimbusds.jose.JOSEException;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signerKey}")
    private String signerKey;

    @Autowired
    private AuthenticationService authenticationService;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Autowired
    private TokenUtils tokenUtils;


    @Override
    public Jwt decode(String token) throws JwtException {

        try {
            boolean response = tokenUtils.introspect(token);
            if (!response) {
                throw new JwtException("Token invalid");
            }
        } catch (JOSEException | ParseException | AppException e) {
            // Log the error and rethrow it as a JwtException
            System.err.println("Error decoding JWT token: " + e.getMessage());
            //throw new JwtException("Token invalid");
        }

        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}