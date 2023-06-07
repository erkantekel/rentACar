package com.example.rentACar.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalInfo {
    private LocalDate startDate;
    private LocalDate endDate;
    private String userName;

}
