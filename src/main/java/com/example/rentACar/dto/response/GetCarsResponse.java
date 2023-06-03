package com.example.rentACar.dto.response;

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
public class GetCarsResponse {
    private String id;
    private String carBrand;
    private String model;
    private String gear;
    private String fuelType;
    private Integer km;
    private Integer year;
    private String color;
    private Double dailyRentalPrice;
    private List<String> images;
    private String description;
    private String ownerId;


    public static GetCarsResponse carToGetCarsResponse(Car request){
        return GetCarsResponse.builder()
                .carBrand(request.getCarBrand().getDescription())
                .model(request.getModel())
                .gear(request.getGear().getDescription())
                .fuelType(request.getFuelType().getDescription())
                .km(request.getKm())
                .year(request.getYear())
                .color(request.getColor())
                .dailyRentalPrice(request.getDailyRentalPrice())
                .images(request.getImages())
                .description(request.getDescription())
                .ownerId(request.getOwnerId())
                .id(request.getId())
                .build();
    }
}
