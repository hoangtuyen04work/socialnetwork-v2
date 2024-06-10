package com.hoangtuyen04work.instagram2.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenResponse {

    private String token;
    private boolean authenticated;

}

