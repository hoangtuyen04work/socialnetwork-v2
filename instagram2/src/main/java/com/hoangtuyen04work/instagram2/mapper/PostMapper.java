package com.hoangtuyen04work.instagram2.mapper;

import com.hoangtuyen04work.instagram2.dto.request.PostRequest;
import com.hoangtuyen04work.instagram2.dto.response.PostResponse;
import com.hoangtuyen04work.instagram2.entity.PostEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PostMapper {
    public PostEntity toPostEntity(PostRequest postRequest);

    public PostResponse toPostResponse(PostEntity postEntity);
}
