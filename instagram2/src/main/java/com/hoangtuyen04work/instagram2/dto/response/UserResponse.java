package com.hoangtuyen04work.instagram2.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String userName;

    private String userId;

    private String DOB;

    private Set<RoleResponse> roles;

}
