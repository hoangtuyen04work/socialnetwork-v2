package com.hoangtuyen04work.socialnetwork.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortenProfile {
    String id;
    String userName;
    String userId;
}
