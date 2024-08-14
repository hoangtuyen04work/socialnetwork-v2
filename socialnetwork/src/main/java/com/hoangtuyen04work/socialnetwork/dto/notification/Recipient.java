package com.hoangtuyen04work.socialnetwork.dto.notification;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipient {
    private String name;
    private String email;
}

