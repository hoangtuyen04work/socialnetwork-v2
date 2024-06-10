package com.hoangtuyen04work.instagram2.service.impl;

import com.hoangtuyen04work.instagram2.entity.InvalidatedTokenEntity;
import com.hoangtuyen04work.instagram2.repository.InvalidatedTokenRepository;
import com.hoangtuyen04work.instagram2.service.interfaces.InvalidatedTokenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

@Service
public class InvalidatedTokenService implements InvalidatedTokenServiceInterface {
    @Autowired
    private InvalidatedTokenRepository tokenRepository;


    @Override
    public InvalidatedTokenEntity save(InvalidatedTokenEntity invalidatedToken) {
        return tokenRepository.save(invalidatedToken);
    }
}
