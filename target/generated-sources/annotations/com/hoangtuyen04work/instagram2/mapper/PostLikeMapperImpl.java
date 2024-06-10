package com.hoangtuyen04work.instagram2.mapper;

import com.hoangtuyen04work.instagram2.dto.request.PostLikeRequest;
import com.hoangtuyen04work.instagram2.entity.PostLikeEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-08T00:08:47+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class PostLikeMapperImpl implements PostLikeMapper {

    @Override
    public PostLikeEntity toPostLikeEntity(PostLikeRequest postLikeRequest) {
        if ( postLikeRequest == null ) {
            return null;
        }

        PostLikeEntity postLikeEntity = new PostLikeEntity();

        return postLikeEntity;
    }
}
