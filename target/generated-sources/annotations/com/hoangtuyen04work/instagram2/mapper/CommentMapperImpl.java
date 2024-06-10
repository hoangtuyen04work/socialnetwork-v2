package com.hoangtuyen04work.instagram2.mapper;

import com.hoangtuyen04work.instagram2.dto.request.CommentRequest;
import com.hoangtuyen04work.instagram2.dto.response.CommentResponse;
import com.hoangtuyen04work.instagram2.entity.CommentEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-08T00:08:47+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentEntity toCommentEntity(CommentRequest commentRequest) {
        if ( commentRequest == null ) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setContent( commentRequest.getContent() );

        return commentEntity;
    }

    @Override
    public CommentResponse toCommentResponse(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setId( commentEntity.getId() );
        commentResponse.setContent( commentEntity.getContent() );

        return commentResponse;
    }
}
