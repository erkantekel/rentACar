package com.example.rentACar.service;


import com.example.rentACar.dto.UserDto;
import com.example.rentACar.dto.enums.ErrorCode;
import com.example.rentACar.dto.enums.Role;
import com.example.rentACar.dto.request.LoginRequest;
import com.example.rentACar.dto.request.SignUpRequest;
import com.example.rentACar.dto.response.TokenResponse;
import com.example.rentACar.entity.User;
import com.example.rentACar.exception.GenericException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    private final PasswordEncoder encoder;

    public TokenResponse login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return TokenResponse
                    .builder()
                    .accessToken(tokenService.generateToken(auth))
                    .user(userService.findUser(loginRequest.getUsername()))
                    .build();
        } catch (final BadCredentialsException badCredentialsException) {
            throw GenericException.builder().httpStatus(HttpStatus.NOT_FOUND).errorCode(ErrorCode.USER_NOT_FOUND).errorMessage("Invalid Username or Password").build();
        }
    }

    public UserDto signup(SignUpRequest signUpRequest){
        boolean isAllReadyRegistered = userService.existsByUsername(signUpRequest.getUsername());

        if(isAllReadyRegistered) {
            throw GenericException.builder().httpStatus(HttpStatus.FOUND)
                    .errorMessage("Username" + signUpRequest.getUsername() + "is already used").build();
        }

        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .role(Role.valueOfLabel(signUpRequest.getRole()))
                .build();

        User fromDb = userService.create(user);

        return UserDto.builder()
                .id(fromDb.getId())
                .username(fromDb.getUsername())
                .role(fromDb.getRole())
                .build();

    }
}
