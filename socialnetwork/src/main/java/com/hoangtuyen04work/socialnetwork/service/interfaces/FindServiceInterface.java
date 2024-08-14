package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.ShortenProfile;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

import java.util.List;

public interface FindServiceInterface {
    List<ShortenProfile> findUser(String finded, Long page) throws AppException;

    List<PostResponse> findPost(String finded, Long page) throws AppException;
}
