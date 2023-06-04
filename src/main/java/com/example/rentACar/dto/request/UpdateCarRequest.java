package com.example.rentACar.dto.request;

import com.example.rentACar.dto.enums.CarBrand;
import com.example.rentACar.dto.enums.CarFuelType;
import com.example.rentACar.dto.enums.CarGearType;
import com.example.rentACar.entity.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UpdateCarRequest {
    @JsonIgnore
    private String id;
    private Integer km;
    private Double dailyRentalPrice;
    private List<String> images;
    private String description;

}
