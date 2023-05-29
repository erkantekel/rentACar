package com.example.rentACar.entity;

import com.example.rentACar.dto.enums.CarBrand;
import com.example.rentACar.dto.enums.CarFuelType;
import com.example.rentACar.dto.enums.CarGearType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "car")
public class Car extends Vehicle {

    @Enumerated(EnumType.STRING)
    private CarBrand carBrand;

    private String model;

    @Enumerated(EnumType.STRING)
    private CarGearType gear;

    @Enumerated(EnumType.STRING)
    private CarFuelType fuelType;

    private Integer km;

}

