package com.example.rentACar.entity;

import com.example.rentACar.dto.enums.Role;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;

    private String password;

    private Double balance;

    @Enumerated(EnumType.STRING)
    private Role role;
}

