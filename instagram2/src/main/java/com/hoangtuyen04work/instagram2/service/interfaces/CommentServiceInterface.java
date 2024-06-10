package com.hoangtuyen04work.instagram2.service.interfaces;

import com.hoangtuyen04work.instagram2.dto.request.CommentRequest;
import com.hoangtuyen04work.instagram2.dto.response.CommentResponse;
import com.hoangtuyen04work.instagram2.exception.AppException;

import java.util.List;
import java.util.Set;

public interface CommentServiceInterface {
    public CommentResponse addComment(String postId, CommentRequest commentRequest) throws AppException;
    public Set<CommentResponse> getAllComment(String postId) throws AppException;
    public CommentResponse getComment(String commentId) throws AppException;
    public CommentResponse updateComment(String commentId, CommentRequest commentRequest) throws AppException;
    public void deleteComment(String commentId) throws AppException;
}
