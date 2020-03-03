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
    BLACK("chernyj", "Чёрный"),
    WHITE("belyj", "Белый"),
    RED("krasnyj", "Красный");

    private final String code;
    private final String name;
}