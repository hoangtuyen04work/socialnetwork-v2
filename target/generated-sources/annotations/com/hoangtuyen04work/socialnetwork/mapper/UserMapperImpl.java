package com.hoangtuyen04work.socialnetwork.mapper;

import com.hoangtuyen04work.socialnetwork.dto.request.UserRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.RoleResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.ShortenProfile;
import com.hoangtuyen04work.socialnetwork.dto.response.UserResponse;
import com.hoangtuyen04work.socialnetwork.entity.RoleEntity;
import com.hoangtuyen04work.socialnetwork.entity.UserEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-14T17:41:15+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;
    private final DatatypeFactory datatypeFactory;

    public UserMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public UserResponse toUserResponse(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.roles( roleEntitySetToRoleResponseSet( userEntity.getRoles() ) );
        userResponse.id( userEntity.getId() );
        userResponse.userName( userEntity.getUserName() );
        userResponse.userId( userEntity.getUserId() );
        if ( userEntity.getDOB() != null ) {
            userResponse.DOB( DateTimeFormatter.ISO_LOCAL_DATE.format( userEntity.getDOB() ) );
        }
        userResponse.email( userEntity.getEmail() );
        userResponse.createdAt( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( userEntity.getCreatedAt() ) ) );

        return userResponse.build();
    }

    @Override
    public UserEntity toUserEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( userRequest.getId() );
        userEntity.userId( userRequest.getUserId() );
        userEntity.userName( userRequest.getUserName() );
        userEntity.password( userRequest.getPassword() );

        return userEntity.build();
    }

    @Override
    public ShortenProfile toShortenProfile(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        ShortenProfile.ShortenProfileBuilder shortenProfile = ShortenProfile.builder();

        shortenProfile.id( userEntity.getId() );
        shortenProfile.userName( userEntity.getUserName() );
        shortenProfile.userId( userEntity.getUserId() );

        return shortenProfile.build();
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

    protected Set<RoleResponse> roleEntitySetToRoleResponseSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResponse> set1 = new LinkedHashSet<RoleResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleMapper.toRoleResponse( roleEntity ) );
        }

        return set1;
    }
}
