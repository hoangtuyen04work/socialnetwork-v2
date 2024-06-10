package com.hoangtuyen04work.instagram2.service.interfaces;


import com.hoangtuyen04work.instagram2.dto.request.PostLikeRequest;
import com.hoangtuyen04work.instagram2.entity.PostLikeEntity;
import com.hoangtuyen04work.instagram2.exception.AppException;

public interface PostLikeSeviceInterface {
    public void addAndRemovePostLike(PostLikeRequest postLikeRequest) throws AppException;
    public boolean isLiked(PostLikeEntity postLikeEntity) throws AppException;
    public long countLikes(String postId);
}
