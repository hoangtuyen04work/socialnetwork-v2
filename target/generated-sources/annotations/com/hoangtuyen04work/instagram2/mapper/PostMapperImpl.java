package com.hoangtuyen04work.instagram2.mapper;

import com.hoangtuyen04work.instagram2.dto.request.PostRequest;
import com.hoangtuyen04work.instagram2.dto.response.PostResponse;
import com.hoangtuyen04work.instagram2.entity.PostEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-08T00:08:46+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostEntity toPostEntity(PostRequest postRequest) {
        if ( postRequest == null ) {
            return null;
        }

        PostEntity.PostEntityBuilder postEntity = PostEntity.builder();

        postEntity.title( postRequest.getTitle() );
        postEntity.content( postRequest.getContent() );

        return postEntity.build();
    }

    @Override
    public PostResponse toPostResponse(PostEntity postEntity) {
        if ( postEntity == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setId( postEntity.getId() );
        postResponse.setTitle( postEntity.getTitle() );
        postResponse.setContent( postEntity.getContent() );

        return postResponse;
    }
}
