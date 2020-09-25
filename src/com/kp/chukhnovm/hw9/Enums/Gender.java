package com.kp.chukhnovm.hw9.Enums;

public enum Gender {
    MALE("Male"), FEMALE("Female");

    private final String description;

    private Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "description='" + description + '\'' +
                '}';
    }
}
