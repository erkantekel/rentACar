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
public class SaveCarResponse {
    private CarBrand carBrand;
    private String model;
    private CarGearType gear;
    private CarFuelType fuelType;
    private Integer km;
    private Integer year;
    private String color;
    private Double dailyRentalPrice;
    private List<String> images;
    private String description;
    private String ownerId;


    public static SaveCarResponse carToSaveCarResponse(Car request){
        return SaveCarResponse.builder()
                .carBrand(request.getCarBrand())
                .model(request.getModel())
                .gear(request.getGear())
                .fuelType(request.getFuelType())
                .km(request.getKm())
                .year(request.getYear())
                .color(request.getColor())
                .dailyRentalPrice(request.getDailyRentalPrice())
                .images(request.getImages())
                .description(request.getDescription())
                .ownerId(request.getOwnerId())
                .build();
    }
}
