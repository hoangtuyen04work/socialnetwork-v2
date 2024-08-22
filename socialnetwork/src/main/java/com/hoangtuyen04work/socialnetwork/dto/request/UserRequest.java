package com.hoangtuyen04work.socialnetwork.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    String id;
    String userId;
    String userName;
    String password;
    MultipartFile multipartFile;
}
