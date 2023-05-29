package com.example.rentACar.dto.enums;

public enum
CarFuelType {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    NOT_DEFINED("NotDefined");
    private final String description;
    CarFuelType(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public static CarFuelType valueOfLabel (String description){
        for (CarFuelType carFuelType : values()) {
            if (carFuelType.description.equalsIgnoreCase(description)){
                return carFuelType;
            }
        }
        return NOT_DEFINED;
    }
}
