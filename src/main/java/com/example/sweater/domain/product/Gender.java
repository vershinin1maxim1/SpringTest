package com.example.sweater.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Gender {
    MEN("muzhskoj", "Мужской"),
    WOMEN("zhenskij", "Женский");


    private final String code;
    private final String name;
}