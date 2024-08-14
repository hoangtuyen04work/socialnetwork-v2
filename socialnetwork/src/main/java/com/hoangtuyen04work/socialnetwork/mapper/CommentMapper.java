package com.hoangtuyen04work.socialnetwork.mapper;


import com.hoangtuyen04work.socialnetwork.dto.request.CommentRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.CommentResponse;
import com.hoangtuyen04work.socialnetwork.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentEntity toCommentEntity(CommentRequest commentRequest);
    CommentResponse toCommentResponse(CommentEntity commentEntity);

}
