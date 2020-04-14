package com.glassesShop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Gender {
    MEN(12,"muzhskie", "Мужские"),
    WOMEN(13,"zhenskie", "Женские"),
    UNISEX(14,"uniseks", "Унисекс"),
    CHILDREN(15,"detskie", "Детские");


    private final Integer id;
    private final String code;
    private final String name;
}