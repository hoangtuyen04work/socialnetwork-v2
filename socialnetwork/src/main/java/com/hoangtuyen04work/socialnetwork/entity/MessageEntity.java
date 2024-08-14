package com.hoangtuyen04work.socialnetwork.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message")
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String message;

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    UserEntity sender;

    @ManyToOne
    @JoinColumn(nullable = false)
    UserEntity receiver;
}
