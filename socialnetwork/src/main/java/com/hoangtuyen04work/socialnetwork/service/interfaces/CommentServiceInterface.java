package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.request.CommentRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.IdRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.CommentResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

import java.util.Set;

public interface CommentServiceInterface {
    Long countComment(String id);

    CommentResponse add(CommentRequest commentRequest) throws AppException;
    Set<CommentResponse> getAll(String id) throws AppException;
    CommentResponse get(String id) throws AppException;
    CommentResponse update(CommentRequest commentRequest) throws AppException;
    void delete(CommentRequest commentRequest) throws AppException;
}
