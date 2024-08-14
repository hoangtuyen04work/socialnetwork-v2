package com.hoangtuyen04work.socialnetwork.repository;

import com.hoangtuyen04work.socialnetwork.entity.MessageEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, String> {

    @Query(value = "SELECT * FROM message WHERE sender_id = :senderId AND receiver_id = :receiverId ORDER BY created_at DESC LIMIT 20 OFFSET :offset", nativeQuery = true)
    List<MessageEntity> findBySenderAndReceiverOrderByCreatedAtDesc(@Param("senderId") String senderId, @Param("receiverId") String receiverId, @Param("offset") Long offset);
}