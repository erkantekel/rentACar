package com.example.rentACar.controller;

import com.example.rentACar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/book")
@RestController
@RequiredArgsConstructor
public class CarController {
    private final UserService userService;

}
