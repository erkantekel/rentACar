package com.example.rentACar.dto.enums;

public enum CarGearType {
    MANUAL("Manual"),
    AUTOMATIC("Automatic"),
    SEMI_AUTOMATIC("SemiAutomatic"),
    CVT("CVT"),
    NOT_DEFINED("NotDefined");

    private final String description;
    CarGearType(String description) {
        this.description =description;
    }
    public static CarGearType valueOfLabel(String description){
        for (CarGearType carGearType : values()){
            if (carGearType.description.equalsIgnoreCase(description)){
                return carGearType;
            }
        }
        return NOT_DEFINED;
    }
}
