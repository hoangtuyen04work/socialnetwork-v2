package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.MessageRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.MessageResponse;
import com.hoangtuyen04work.socialnetwork.entity.MessageEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-14T17:41:15+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageEntity toMessageEntity(MessageRequest message) {
        if ( message == null ) {
            return null;
        }

        MessageEntity.MessageEntityBuilder messageEntity = MessageEntity.builder();

        messageEntity.message( message.getMessage() );

        return messageEntity.build();
    }

    @Override
    public MessageResponse toMessageResponse(MessageEntity message) {
        if ( message == null ) {
            return null;
        }

        MessageResponse messageResponse = new MessageResponse();

        messageResponse.setMessage( message.getMessage() );
        messageResponse.setCreatedAt( message.getCreatedAt() );

        return messageResponse;
    }
}
