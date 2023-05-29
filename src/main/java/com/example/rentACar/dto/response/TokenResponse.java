package com.example.rentACar.dto.response;

import com.example.rentACar.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TokenResponse {
    private String accessToken;
    private UserDto user;
}
