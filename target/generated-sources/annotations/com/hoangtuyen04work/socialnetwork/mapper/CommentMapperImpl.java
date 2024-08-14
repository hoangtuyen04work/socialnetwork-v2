package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.CommentRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.CommentResponse;
import com.hoangtuyen04work.socialnetwork.entity.CommentEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-14T17:41:15+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    private final DatatypeFactory datatypeFactory;

    public CommentMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public CommentEntity toCommentEntity(CommentRequest commentRequest) {
        if ( commentRequest == null ) {
            return null;
        }

        CommentEntity.CommentEntityBuilder commentEntity = CommentEntity.builder();

        commentEntity.content( commentRequest.getContent() );

        return commentEntity.build();
    }

    @Override
    public CommentResponse toCommentResponse(CommentEntity commentEntity) {
        if ( commentEntity == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setId( commentEntity.getId() );
        commentResponse.setContent( commentEntity.getContent() );
        commentResponse.setCreatedAt( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( commentEntity.getCreatedAt() ) ) );

        return commentResponse;
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }
}
