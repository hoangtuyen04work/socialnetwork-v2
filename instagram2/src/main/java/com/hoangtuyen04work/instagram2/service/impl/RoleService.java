package com.hoangtuyen04work.instagram2.service.impl;

import com.hoangtuyen04work.instagram2.entity.RoleEntity;
import com.hoangtuyen04work.instagram2.repository.RoleRepository;
import com.hoangtuyen04work.instagram2.service.interfaces.RoleEntityInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleEntityInterface {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity findRole(String roleName) {
        return roleRepository.findByRole(roleName);
    }
}
