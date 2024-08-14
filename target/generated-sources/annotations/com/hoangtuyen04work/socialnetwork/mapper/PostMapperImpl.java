package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.NewPostRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.entity.PostEntity;
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
public class PostMapperImpl implements PostMapper {

    private final DatatypeFactory datatypeFactory;

    public PostMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public PostEntity toPostEntity(NewPostRequest newPostRequest) {
        if ( newPostRequest == null ) {
            return null;
        }

        PostEntity.PostEntityBuilder postEntity = PostEntity.builder();

        postEntity.title( newPostRequest.getTitle() );
        postEntity.content( newPostRequest.getContent() );

        return postEntity.build();
    }

    @Override
    public PostResponse toPostResponse(PostEntity postEntity) {
        if ( postEntity == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setId( postEntity.getId() );
        postResponse.setTitle( postEntity.getTitle() );
        postResponse.setContent( postEntity.getContent() );
        postResponse.setCreatedAt( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( postEntity.getCreatedAt() ) ) );

        return postResponse;
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
