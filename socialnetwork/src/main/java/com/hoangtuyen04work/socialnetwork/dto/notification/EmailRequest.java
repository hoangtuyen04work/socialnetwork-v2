package com.hoangtuyen04work.socialnetwork.dto.notification;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    Sender sender;
    List<Recipient> to;
    String subject;
    String htmlContent;
}
