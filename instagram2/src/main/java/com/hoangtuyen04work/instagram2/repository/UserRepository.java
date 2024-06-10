package com.hoangtuyen04work.instagram2.repository;

import com.hoangtuyen04work.instagram2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    public boolean existsByUserId(String userId);
    public UserEntity findByUserId(String userId);
    public boolean existsById(String Id);

}