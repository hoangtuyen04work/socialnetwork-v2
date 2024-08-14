package com.hoangtuyen04work.socialnetwork.service.impl;

import com.hoangtuyen04work.socialnetwork.dto.request.MessageRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.MessageResponse;
import com.hoangtuyen04work.socialnetwork.entity.MessageEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.mapper.MessageMapper;
import com.hoangtuyen04work.socialnetwork.repository.MessageRepository;
import com.hoangtuyen04work.socialnetwork.service.interfaces.MessageServiceInterface;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class MessageService implements MessageServiceInterface {
    UserService userService;
    MessageRepository messageRepository;
    MessageMapper messageMapper;
    @Override
    public String sendMessage(MessageRequest request) throws AppException {
        MessageEntity message = MessageEntity.builder()
                .message(request.getMessage())
                .sender(userService.getUserInHolder())
                .receiver(userService.findById(request.getReceiverId()))
                .build();
        messageRepository.save(message);
        return request.getMessage();
    }
    @Override
    public String deleteMessage(String id){
        messageRepository.deleteById(id);
        return id;
    }
    @Override
    public List<MessageResponse> messages(String receiverId, Long page) throws AppException {
        UserEntity sender = userService.getUserInHolder();
        UserEntity receiver = userService.findById(receiverId);
        List<MessageEntity> sent = messageRepository.findBySenderAndReceiverOrderByCreatedAtDesc(
                                                            userService.getIdInHolder(), receiverId,
                                                        page* 20 - 20);
        List<MessageEntity> received = messageRepository.findBySenderAndReceiverOrderByCreatedAtDesc(
                                                            receiverId, userService.getIdInHolder(),
                                                        page* 20 - 20);
        return orderMessage(sent, received);
    }

    @Override
    public List<MessageResponse> orderMessage(List<MessageEntity> sent, List<MessageEntity> received){
        List<MessageResponse> responses = new ArrayList<>();
        while(!sent.isEmpty() || !received.isEmpty()){
            if(sent.getFirst().getCreatedAt().isAfter(received.getFirst().getCreatedAt()) || received.isEmpty()){
                responses.addLast(messageMapper.toMessageResponse(sent.getFirst()));
                sent.removeFirst();
            }
            else{
                responses.addLast(messageMapper.toMessageResponse(received.getFirst()));
                sent.removeFirst();
            }
        }
        return responses;
    }

}
