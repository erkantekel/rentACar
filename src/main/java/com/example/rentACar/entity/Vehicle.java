package com.example.rentACar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
public abstract class Vehicle extends BaseEntity {

    private Integer year;

    private String color;

    private List<RentalInfo> rentalInfos;

    private Double dailyRentalPrice;

    private List<String> images;

    private String description;

    private String ownerId;

}
