package com.hoangtuyen04work.socialnetwork.controller;


import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.notification.EmailResponse;
import com.hoangtuyen04work.socialnetwork.dto.notification.SendEmailRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.NotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationEmailController {
    NotificationService notificationService;

    @PostMapping("/notification/sendemail")
    ApiResponse<EmailResponse> sendEmail(@RequestBody SendEmailRequest sendEmailRequest) throws AppException {
        return ApiResponse.<EmailResponse>builder()
                .data(notificationService.sendEmail(sendEmailRequest))
                .message(NoticeResponse.success)
                .build();
    }
}
