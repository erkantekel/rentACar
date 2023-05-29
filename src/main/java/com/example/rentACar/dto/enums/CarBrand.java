package com.example.rentACar.dto.enums;

import lombok.Getter;

@Getter
public enum CarBrand {
    TOYOTA("Toyota"),
    HONDA("Honda"),
    BMW("BMW"),
    MERCEDES("Mercedes"),
    AUDI("Audi"),
    VOLKSWAGEN("Volkswagen"),
    FORD("Ford"),
    CHEVROLET("Chevrolet"),
    HYUNDAI("Hyundai"),
    NISSAN("Nissan"),
    KIA("Kia"),
    MAZDA("Mazda"),
    SUBARU("Subaru"),
    PEUGEOT("Peugeot"),
    FIAT("Fiat"),
    NOT_DEFINED("NotDefined");

    private final String description;

    CarBrand(String description) {
        this.description = description;
    }

    public static CarBrand valueOfLabel(String description){
        for (CarBrand carBrand : values()){
            if (carBrand.description.equalsIgnoreCase(description)){
                return carBrand;
            }
        }
        return NOT_DEFINED;
    }
}