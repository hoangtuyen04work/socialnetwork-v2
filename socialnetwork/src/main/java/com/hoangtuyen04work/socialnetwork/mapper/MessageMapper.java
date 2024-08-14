package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.MessageRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.MessageResponse;
import com.hoangtuyen04work.socialnetwork.entity.MessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageEntity toMessageEntity(MessageRequest message);
    MessageResponse toMessageResponse(MessageEntity message);
}
