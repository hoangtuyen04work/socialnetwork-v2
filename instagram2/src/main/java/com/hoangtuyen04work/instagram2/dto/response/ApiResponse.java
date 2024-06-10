package com.hoangtuyen04work.instagram2.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApiResponse<T>{


    @Builder.Default
    private int code = 1000;
    private String message;
    private T data;

}
