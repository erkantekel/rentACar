package com.example.rentACar.service;


import com.example.rentACar.dto.UserDto;
import com.example.rentACar.entity.User;
import com.example.rentACar.exception.GenericException;
import com.example.rentACar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.function.Supplier;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(userNotFound(HttpStatus.NOT_FOUND));
    }

    public UserDto findUser(String username) {
        var user = findUserByUsername(username);
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public UserDto validateUser() {
        final Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).orElseThrow(userNotFound(HttpStatus.UNAUTHORIZED));
        final UserDetails details = Optional.ofNullable((UserDetails) authentication.getPrincipal()).orElseThrow(userNotFound(HttpStatus.UNAUTHORIZED));
        return findUser(details.getUsername());
    }

    private static Supplier<GenericException> userNotFound(HttpStatus unauthorized) {
        return () -> GenericException.builder().httpStatus(unauthorized).errorMessage("User not found!").build();
    }

    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

}
