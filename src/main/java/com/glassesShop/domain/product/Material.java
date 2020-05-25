package com.glassesShop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Material {
    METALL(83,"metall", "Металл"),
    TITAN(84,"titan", "Титан"),
    PLASTIK(85,"plastik", "Пластик"),
    SBORKA(86,"sborka", "Сборка");


    private final Integer id;
    private final String code;
    private final String rusName;//просто name вызовет ошибку в ftl
}