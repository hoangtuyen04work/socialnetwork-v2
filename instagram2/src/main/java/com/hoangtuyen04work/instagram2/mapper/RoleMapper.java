package com.hoangtuyen04work.instagram2.mapper;

import com.hoangtuyen04work.instagram2.dto.response.RoleResponse;
import com.hoangtuyen04work.instagram2.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "role", source = "role")
    RoleResponse toRoleResponse(RoleEntity roleEntity);

}
