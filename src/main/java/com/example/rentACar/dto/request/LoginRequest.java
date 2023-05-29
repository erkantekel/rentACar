package com.example.rentACar.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LoginRequest {

    private String username;
    private String password;

}