package com.example.rentACar.entity;

import com.example.rentACar.dto.enums.CarBrand;
import com.example.rentACar.dto.enums.CarFuelType;
import com.example.rentACar.dto.enums.CarGearType;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "car")
@SuperBuilder
public class Car extends Vehicle {

    @Enumerated(EnumType.STRING)
    @NotNull
    private CarBrand carBrand;

    @NotNull
    private String model;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CarGearType gear;

    @Enumerated(EnumType.STRING)
    @NotNull
    private CarFuelType fuelType;

    @Positive
    private Integer km;

}

