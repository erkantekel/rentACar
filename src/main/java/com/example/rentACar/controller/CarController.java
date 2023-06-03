package com.example.rentACar.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import com.example.rentACar.dto.request.GetCarsRequest;
import com.example.rentACar.dto.request.SaveCarRequest;
import com.example.rentACar.dto.response.GetCarsResponse;
import com.example.rentACar.dto.response.SaveCarResponse;
import com.example.rentACar.service.CarService;
import com.example.rentACar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<GetCarsResponse>> getCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String carBrand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Double maxRentalPrice,
            @RequestParam(required = false) Double minRentalPrice,
            @RequestParam(required = false) String sortField,
            @RequestParam(required = false) String sortDirection
    ){
        Pageable pageable;
        Sort.Direction direction = Sort.Direction.ASC;
        if (("DESC").equalsIgnoreCase(sortDirection)) {
            direction = Sort.Direction.DESC;
        }
        if (sortField != null && !sortField.isEmpty()) {
            try {
                Sort sort = Sort.by(direction, sortField);
                pageable = PageRequest.of(page, size, sort);
            } catch (IllegalArgumentException e) {
                pageable = PageRequest.of(page, size);
            }
        }else{
            pageable = PageRequest.of(page, size);

        }

        GetCarsRequest request = GetCarsRequest.builder()
                .maxRentalPrice(maxRentalPrice)
                .minRentalPrice(minRentalPrice)
                .carBrand(carBrand)
                .model(model)
                .build();
        return ResponseEntity.ok(carService.getCars(request,pageable));
    }

}

