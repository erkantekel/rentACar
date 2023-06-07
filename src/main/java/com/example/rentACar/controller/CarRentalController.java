package com.example.rentACar.controller;

import com.example.rentACar.dto.enums.ErrorCode;
import com.example.rentACar.dto.request.RentalRequest;
import com.example.rentACar.dto.response.GetCarsResponse;
import com.example.rentACar.dto.response.RentalResponse;
import com.example.rentACar.entity.Car;
import com.example.rentACar.exception.GenericException;
import com.example.rentACar.service.RentalService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/car-rental")
public class CarRentalController {
    private final RentalService rentalService ;

    public CarRentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/available-cars")
    public ResponseEntity<List<GetCarsResponse>> getAvailableCars(@RequestParam("startDate") String startDateStr,
                                                                  @RequestParam("endDate") String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        if (startDate.isBefore(LocalDate.now())) {
            throw GenericException.builder()
                    .errorCode(ErrorCode.INVALID_DATE)
                    .build();
        }
        List<GetCarsResponse> availableCars = rentalService.findAvailableCars(startDate, endDate);
        return ResponseEntity.ok(availableCars);
    }

    @PostMapping("/rent")
    public ResponseEntity<RentalResponse> rentCar(@RequestBody RentalRequest rentalRequest) {
        RentalResponse response = rentalService.rent(rentalRequest);

        return ResponseEntity.ok(response);
    }


}
