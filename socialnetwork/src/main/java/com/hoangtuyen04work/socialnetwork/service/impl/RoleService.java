package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.entity.RoleEntity;
import com.hoangtuyen04work.socialnetwork.repository.RoleRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.RoleEntityInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class RoleService implements RoleEntityInterface {
    RoleRepository roleRepository;

    @Override
    public RoleEntity findRole(String roleName) {
        return roleRepository.findByRole(roleName);
    }
}
