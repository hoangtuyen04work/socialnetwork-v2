package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query(value = "SELECT * FROM user  user WHERE user.userId LIKE %:finded% LIMIT 10 OFFSET :page", nativeQuery = true)
    List<UserEntity> findByUserIdContaining(@Param("finded") String name,@Param("page") Long page);

    boolean existsByUserId(String userId);
    
    boolean existsById(String Id);

    Optional<UserEntity> findByUserId(String userId);

    Optional<UserEntity> findById(String id);

    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);


}