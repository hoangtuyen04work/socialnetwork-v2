package com.hoangtuyen04work.socialnetwork.controller;

import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.IdResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.PostResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.ShortenProfile;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.FindService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/find")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FindController {
    FindService findService;
    @GetMapping("/user/{finded}/{page}")
    ApiResponse<List<String>> findUser(@PathVariable String finded, @PathVariable Long page) throws AppException {
        System.out.println("Received finded: " + finded + ", page: " + page);
        return ApiResponse.<List<String>>builder()
                .message(NoticeResponse.success)
                .data(findService.findUser(finded, page))
                .build();
    }

    @GetMapping("/post/{finded}/{page}")
    ApiResponse<List<String>> findPost(@PathVariable String finded, @PathVariable Long page) throws AppException {
        System.out.println("Received finded: " + finded + ", page: " + page);
        return ApiResponse.<List<String>>builder()
                .message(NoticeResponse.success)
                .data(findService.findPost(finded, page))
                .build();
    }




}
