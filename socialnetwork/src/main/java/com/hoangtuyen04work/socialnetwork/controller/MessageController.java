package com.hoangtuyen04work.socialnetwork.controller;

import com.hoangtuyen04work.socialnetwork.constant.NoticeResponse;
import com.hoangtuyen04work.socialnetwork.dto.request.IdRequest;
import com.hoangtuyen04work.socialnetwork.dto.request.MessageRequest;
import com.hoangtuyen04work.socialnetwork.dto.response.ApiResponse;
import com.hoangtuyen04work.socialnetwork.dto.response.MessageResponse;
import com.hoangtuyen04work.socialnetwork.exception.AppException;
import com.hoangtuyen04work.socialnetwork.service.impl.MessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MessageController {
    MessageService messageService;

    @PostMapping("/send")
    public ApiResponse<String> sendMessage(@RequestBody MessageRequest messageRequest) throws AppException {
        return ApiResponse.<String>builder()
                .data(messageService.sendMessage(messageRequest))
                .message(NoticeResponse.success)
                .build();
    }

    @GetMapping("/all/{page}")
    public ApiResponse<List<MessageResponse>> messages(@RequestBody IdRequest idRequest, @PathVariable Long page) throws AppException {
        return ApiResponse.<List<MessageResponse>>builder()
                .message(NoticeResponse.success)
                .data(messageService.messages(idRequest.getId(), page))
                .build();
    }
}
