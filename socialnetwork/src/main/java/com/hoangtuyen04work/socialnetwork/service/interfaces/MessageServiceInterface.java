package com.hoangtuyen04work.socialnetwork.service.interfaces;

import com.hoangtuyen04work.socialnetwork.dto.request.MessageRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.MessageResponse;
import com.hoangtuyen04work.socialnetwork.entity.MessageEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;

import java.util.List;

public interface MessageServiceInterface {
    String sendMessage(MessageRequest request) throws AppException;

    String deleteMessage(String id);

    List<MessageResponse> messages(String receiverId, Long page) throws AppException;

    List<MessageResponse> orderMessage(List<MessageEntity> sent, List<MessageEntity> received);
}
