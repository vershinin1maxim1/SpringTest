package com.example.sweater.domain.product;

public enum Gender {
    MEN("muzhskoj", "Мужской"),
    WOMEN("zhenskij", "Женский");


    private final String code;
    private final String name;

    private Gender(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}