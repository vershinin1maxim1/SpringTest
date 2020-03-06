package com.example.sweater.domain.product;

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
    RED(2, "krasnyj", "Красный");

    private final Integer id;
    private final String code;
    private final String name;
}