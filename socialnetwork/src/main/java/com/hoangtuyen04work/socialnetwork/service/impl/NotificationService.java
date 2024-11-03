package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.notification.EmailRequest;
import com.hoangtuyen04work.socialnetwork.dto.notification.EmailResponse;
import com.hoangtuyen04work.socialnetwork.dto.notification.SendEmailRequest;
import com.hoangtuyen04work.socialnetwork.dto.notification.Sender;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.exception.ErrorCode;
import com.hoangtuyen04work.socialnetwork.repository.client.SendEmailClient;
import com.hoangtuyen04work.socialnetwork.service.interfaces.NotificationServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class NotificationService implements NotificationServiceInterface {
    @NonFinal
    @Value("${api.key}")
    String apiKey;
    @NonFinal
    @Value(("${email.name}"))
    String emailSender;
    SendEmailClient sendEmailClient;

    @Override
    public EmailResponse sendEmail(SendEmailRequest sendEmailRequest) throws AppException {
        Sender sender = new Sender();
        sender.setEmail(emailSender);
        sender.setName("Hoang Huu Tuyen");
        EmailRequest emailRequest = EmailRequest.builder()
                .to(sendEmailRequest.getTo())
                .sender(sender)
                .subject(sendEmailRequest.getSubject())
                .htmlContent(sendEmailRequest.getHtmlContent())
                .build();
        try{
            return sendEmailClient.sendEmail(apiKey, emailRequest);
        }
        catch(Exception e){
            System.err.println(e);
            throw new AppException(ErrorCode.SEND_EMAIL_ERROR_CODE);
        }
    }
}
