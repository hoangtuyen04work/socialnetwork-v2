package com.hoangtuyen04work.instagram2.repository;

import com.hoangtuyen04work.instagram2.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    public RoleEntity findByRole(String role);
}
