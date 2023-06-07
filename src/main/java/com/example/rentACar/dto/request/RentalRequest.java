package com.example.rentACar.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RentalRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String userName;
    private String carId;
}
