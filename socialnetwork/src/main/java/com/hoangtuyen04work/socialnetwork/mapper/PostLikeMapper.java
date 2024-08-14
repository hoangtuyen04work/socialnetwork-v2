package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.PostLikeRequest;
import com.hoangtuyen04work.socialnetwork.entity.PostLikeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostLikeMapper {
    PostLikeEntity toPostLikeEntity(PostLikeRequest postLikeRequest);
}
