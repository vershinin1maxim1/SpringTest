package com.glassesShop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum Color {
    BLACK(0, "chernyj", "Чёрный"),
    WHITE(1, "belyj", "Белый"),
    RED(2, "krasnyj", "Красный"),
    BLUE(3, "sinij", "Синий"),
    BROWN(4, "korichnevyj", "Коричневый"),
    PINK(5, "rozovyj", "Розовый"),
    SILVER(6, "serebryanyj", "Серебряный"),
    GREY(7, "seryj", "Серый"),
    GREEN(8, "zelyonyj", "Зелёный"),
    GOLD(9, "zolotoj", "Золотой"),
    PURPLE(10, "fioletovyj", "Фиолетовый"),
    BEIGE(11, "bezhevyj", "Бежевый");

    private final Integer id;
    private final String code;
    private final String rusName;//просто name вызовет ошибку в ftl
}