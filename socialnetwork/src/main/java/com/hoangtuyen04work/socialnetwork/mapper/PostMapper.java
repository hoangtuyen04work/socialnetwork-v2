package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.NewPostRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PostMapper {
    PostEntity toPostEntity(NewPostRequest newPostRequest);
    PostResponse toPostResponse(PostEntity postEntity);
}
