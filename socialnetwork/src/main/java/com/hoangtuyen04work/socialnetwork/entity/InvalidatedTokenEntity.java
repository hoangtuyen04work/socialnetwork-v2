package com.hoangtuyen04work.socialnetwork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invalidatedtoken")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidatedTokenEntity {

    @Id
    String id;
    Date expiryTime;
}
