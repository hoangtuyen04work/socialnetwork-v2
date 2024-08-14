package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ShortenProfile;
import com.hoangtuyen04work.socialnetwork.dto.response.UserResponse;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(target = "roles", source = "roles")
    })
    UserResponse toUserResponse(UserEntity userEntity);
    UserEntity toUserEntity(UserRequest userRequest);
    ShortenProfile toShortenProfile(UserEntity userEntity);
}