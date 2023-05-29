package com.example.rentACar.dto.enums;


public enum Role {
    ADMIN("Admin"),
    USER("User");

    private final String description;
    Role(String description) {
        this.description =description;
    }

    public static Role valueOfLabel(String description){
        for (Role role : values()){
            if (role.description.equalsIgnoreCase(description)){
                return role;
            }
        }
        return USER;
    }
}