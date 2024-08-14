package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.entity.InvalidatedTokenEntity;
import com.hoangtuyen04work.socialnetwork.repository.InvalidatedTokenRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.InvalidatedTokenServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class InvalidatedTokenService implements InvalidatedTokenServiceInterface {
    InvalidatedTokenRepository tokenRepository;

    @Override
    public InvalidatedTokenEntity save(InvalidatedTokenEntity invalidatedToken) {
        return tokenRepository.save(invalidatedToken);
    }
}
