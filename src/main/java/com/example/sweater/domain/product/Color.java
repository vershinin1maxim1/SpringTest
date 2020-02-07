package com.example.sweater.domain.product;

import java.util.HashSet;
import java.util.Set;

public enum Color {
    BLACK("chernyj", "Чёрный"),
    WHITE("belyj", "Белый"),
    RED("krasnyj", "Красный");

    private final String code;
    private final String name;

    private Color(String code, String name) {
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