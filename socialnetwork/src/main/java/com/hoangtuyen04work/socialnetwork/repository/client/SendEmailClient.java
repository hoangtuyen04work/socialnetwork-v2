package com.hoangtuyen04work.socialnetwork.repository.client;


import com.hoangtuyen04work.socialnetwork.dto.notification.EmailRequest;
import com.hoangtuyen04work.socialnetwork.dto.notification.EmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "socialnetwork", url = "https://api.brevo.com")
public interface SendEmailClient {
    @PostMapping(value = "/v3/smtp/email", produces = MediaType.APPLICATION_JSON_VALUE)
    EmailResponse sendEmail(@RequestHeader("api-key") String apiKey, @RequestBody EmailRequest body);
}
