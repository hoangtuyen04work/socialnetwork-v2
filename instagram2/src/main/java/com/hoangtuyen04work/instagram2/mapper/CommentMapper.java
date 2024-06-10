package com.hoangtuyen04work.instagram2.mapper;


import com.hoangtuyen04work.instagram2.dto.request.CommentRequest;
import com.hoangtuyen04work.instagram2.dto.response.CommentResponse;
import com.hoangtuyen04work.instagram2.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    public CommentEntity toCommentEntity(CommentRequest commentRequest);
    public CommentResponse toCommentResponse(CommentEntity commentEntity);

}
