package com.example.rentACar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.MappedSuperclass;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Vehicle extends BaseEntity {

    private Integer year;

    private String color;

    private List<RentalInfo> rentalInfos;

    private Double dailyRentalPrice;

    private List<String> images;

    private String description;

    private String ownerId;

}
