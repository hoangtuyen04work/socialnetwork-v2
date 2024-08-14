package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.notification.EmailResponse;
import com.hoangtuyen04work.socialnetwork.dto.notification.SendEmailRequest;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

public interface NotificationServiceInterface {
    EmailResponse sendEmail(SendEmailRequest sendEmailRequest) throws AppException;
}
