package com.hoangtuyen04work.instagram2.mapper;

import com.hoangtuyen04work.instagram2.dto.request.FollowRequest;
import com.hoangtuyen04work.instagram2.entity.FollowEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-08T00:08:47+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class FollowMapperImpl implements FollowMapper {

    @Override
    public FollowEntity toFollowEntity(FollowRequest followRequest) {
        if ( followRequest == null ) {
            return null;
        }

        FollowEntity followEntity = new FollowEntity();

        return followEntity;
    }
}
