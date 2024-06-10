package com.hoangtuyen04work.instagram2.mapper;

import com.hoangtuyen04work.instagram2.dto.request.FollowRequest;
import com.hoangtuyen04work.instagram2.entity.FollowEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowMapper {
    public FollowEntity toFollowEntity(FollowRequest followRequest);
}
