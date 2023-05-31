package com.example.rentACar.controller;

import com.example.rentACar.dto.request.SaveCarRequest;
import com.example.rentACar.dto.response.SaveCarResponse;
import com.example.rentACar.service.CarService;
import com.example.rentACar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api/v1/car")
@RestController
@RequiredArgsConstructor
public class CarController {
    private final UserService userService;
    private final CarService carService;

    @PostMapping("/save")
    public ResponseEntity<SaveCarResponse> saveCar (@RequestBody @Valid SaveCarRequest request){
        return ResponseEntity.ok(carService.saveCar(request));
    }

}
