package com.example.rentACar.dto.request;

import com.example.rentACar.dto.enums.CarBrand;
import com.example.rentACar.dto.enums.CarFuelType;
import com.example.rentACar.dto.enums.CarGearType;
import com.example.rentACar.entity.Car;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Builder
public class GetCarsRequest {

    private String carBrand;
    private String model;
    private Double maxRentalPrice;
    private Double minRentalPrice;

}
