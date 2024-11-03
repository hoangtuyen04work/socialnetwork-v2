package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.PostLikeRequest;
import com.hoangtuyen04work.socialnetwork.entity.PostLikeEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-07T16:44:10+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class PostLikeMapperImpl implements PostLikeMapper {

    @Override
    public PostLikeEntity toPostLikeEntity(PostLikeRequest postLikeRequest) {
        if ( postLikeRequest == null ) {
            return null;
        }

        PostLikeEntity.PostLikeEntityBuilder postLikeEntity = PostLikeEntity.builder();

        return postLikeEntity.build();
    }
}
