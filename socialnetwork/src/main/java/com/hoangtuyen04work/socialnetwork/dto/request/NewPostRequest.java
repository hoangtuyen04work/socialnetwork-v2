package com.hoangtuyen04work.socialnetwork.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewPostRequest {
    String title;
    String content;
    MultipartFile multipartFile;
}
