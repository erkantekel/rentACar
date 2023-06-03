package com.example.rentACar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Vehicle extends BaseEntity {

    @NotNull
    private Integer year;

    private String color;

    private List<RentalInfo> rentalInfos;

    @Positive
    private Double dailyRentalPrice;

    private List<String> images;

    private String description;

    @NotNull
    private String ownerId;

}
