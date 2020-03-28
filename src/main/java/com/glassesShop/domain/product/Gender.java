package com.glassesShop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Gender {
    MEN(3,"muzhskoj", "Мужской"),
    WOMEN(4,"zhenskij", "Женский");


    private final Integer id;
    private final String code;
    private final String name;
}