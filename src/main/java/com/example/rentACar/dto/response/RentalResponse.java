package com.example.rentACar.dto.response;

import com.example.rentACar.entity.RentalInfo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
public class RentalResponse {
    private List<RentalInfo> rentalInfos;
    private String carId;
}
