package com.hoangtuyen04work.socialnetwork.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponse {
    String type;
    String message;
    LocalDateTime createdAt;
    LocalDateTime deleteAt;
}
