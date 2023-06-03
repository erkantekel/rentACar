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
    private String carBrand;
    @NotBlank
    private String model;
    @NotNull
    private String gear;
    @NotNull
    private String fuelType;
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
                .carBrand(CarBrand.valueOfLabel(request.getCarBrand()))
                .model(request.getModel())
                .gear(CarGearType.valueOfLabel(request.getGear()))
                .fuelType(CarFuelType.valueOfLabel(request.getFuelType()))
                .km(request.getKm())
                .year(request.getYear())
                .color(request.getColor())
                .dailyRentalPrice(request.getDailyRentalPrice())
                .images(request.getImage())
                .description(request.getDescription())
                .build();
    }
}
