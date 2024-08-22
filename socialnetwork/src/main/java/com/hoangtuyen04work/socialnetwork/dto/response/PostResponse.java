package com.hoangtuyen04work.socialnetwork.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponse {
    String id;
    String posterName;
    String posterId;
    String title;
    String content;
    LocalDate createdAt;
    LocalDate updatedAt;
    Long numberLike;
    Long numberComment;
    String imageUrl;
}
