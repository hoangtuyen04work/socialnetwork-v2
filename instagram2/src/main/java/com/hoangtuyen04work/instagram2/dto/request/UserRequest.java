package com.hoangtuyen04work.instagram2.dto.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String userId;

    private String userName;

    private String password;

    private LocalDateTime  DOB;



}
