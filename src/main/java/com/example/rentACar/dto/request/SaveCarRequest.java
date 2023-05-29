package com.example.rentACar.dto.request;

import com.example.rentACar.dto.enums.CarBrand;
import com.example.rentACar.dto.enums.CarFuelType;
import com.example.rentACar.dto.enums.CarGearType;
import com.sun.istack.NotNull;
import lombok.Getter;
import java.util.List;

@Getter
public class SaveCarRequest {
    @NotNull
    private CarBrand carBrand;
    @NotNull
    private String model;
    @NotNull
    private CarGearType gear;
    @NotNull
    private CarFuelType fuelType;
    @NotNull
    private Integer km;
    @NotNull
    private Integer year;
    private String color;
    @NotNull
    private Double dailyRentalPrice;
    private List<String> image;
    private String description;
}
