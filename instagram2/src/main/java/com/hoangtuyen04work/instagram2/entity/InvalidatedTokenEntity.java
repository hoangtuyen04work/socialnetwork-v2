package com.hoangtuyen04work.instagram2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invalidatedtoken")
public class InvalidatedTokenEntity {

    @Id
    private String id;
    private Date expiryTime;
}
