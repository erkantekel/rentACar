package com.example.rentACar.dto;

import com.example.rentACar.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {

    private String username;
    private Role role;
    private String id;


}
