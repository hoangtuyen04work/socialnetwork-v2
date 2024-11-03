package com.hoangtuyen04work.socialnetwork.service;

import com.hoangtuyen04work.socialnetwork.repository.client.AmazonS3Client;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class Amazon3SService {
    @Autowired
    AmazonS3Client amazonS3Client;
    public String addImageS3(MultipartFile multipartFile){
        return amazonS3Client.uploadFile(multipartFile);
    }
}
