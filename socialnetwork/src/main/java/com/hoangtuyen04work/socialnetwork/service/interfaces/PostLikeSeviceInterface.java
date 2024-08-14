package com.hoangtuyen04work.socialnetwork.service.interfaces;
import com.hoangtuyen04work.socialnetwork.dto.request.PostLikeRequest;
import com.hoangtuyen04work.socialnetwork.entity.PostLikeEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

public interface PostLikeSeviceInterface {
    void addAndRemove(PostLikeRequest postLikeRequest) throws AppException;

    PostLikeEntity setPostLikeAttribute(String postId) throws AppException;

    boolean isLiked(PostLikeEntity postLikeEntity) throws AppException;
    long countLikes(String postId) throws AppException;
}
