package com.example.rentACar.dto.request;

import com.example.rentACar.dto.enums.CarBrand;
import com.example.rentACar.dto.enums.CarFuelType;
import com.example.rentACar.dto.enums.CarGearType;
import com.example.rentACar.entity.Car;
import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class SaveCarRequest {
    @NotNull
    private CarBrand carBrand;
    @NotBlank
    private String model;
    @NotNull
    private CarGearType gear;
    @NotNull
    private CarFuelType fuelType;
    @NotBlank
    private Integer km;
    @NotNull
    private Integer year;
    private String color;
    @NotNull
    private Double dailyRentalPrice;
    private List<String> image;
    private String description;
    @NotNull
    private String ownerId;


    public static Car saveCarRequestToCar(SaveCarRequest request){
        return Car.builder()
                .carBrand(request.getCarBrand())
                .model(request.getModel())
                .gear(request.getGear())
                .fuelType(request.getFuelType())
                .km(request.getKm())
                .year(request.getYear())
                .color(request.getColor())
                .dailyRentalPrice(request.getDailyRentalPrice())
                .images(request.getImage())
                .description(request.getDescription())
                .build();
    }
}
