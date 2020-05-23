package com.glassesShop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FrameForm {
    BABOCHKA(16,"babochka", "Бабочка"),
    KOSHACHIJ_GLAZ(17,"koshachij_glaz", "Кошачий глаз"),
    OVALNAYA(18,"ovalnaya", "Овальная"),
    PANTO(19,"panto", "Панто"),
    PILOT(20,"pilot", "Пилот"),
    KRUGLAYA(21,"kruglaya", "Круглая"),
    KVADRATNAYA(22,"kvadratnaya", "Квадратная"),
    PRYAMOUGOLNAYA(23,"pryamougolnaya", "Прямоугольная"),
    TRAPECIYA(24,"trapeciya", "Трапеция"),
    BROULAJNER(25,"broulajner", "Броулайнер"),
    VAJFARER(26,"vajfarer", "Вайфарер"),
    NESTANDARTNAYA(27,"nestandartnaya", "Нестандартная");

    private final Integer id;
    private final String code;
    private final String rusName;//просто name вызовет ошибку в ftl
}