package com.hoangtuyen04work.instagram2.mapper;

import com.hoangtuyen04work.instagram2.dto.request.PostLikeRequest;
import com.hoangtuyen04work.instagram2.entity.PostLikeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostLikeMapper {
    public PostLikeEntity toPostLikeEntity(PostLikeRequest postLikeRequest);
}
