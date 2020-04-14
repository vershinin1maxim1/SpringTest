package com.glassesShop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
public enum AttributeEnum {
    //убедиться, что id не повторяются
    BLACK(Color.BLACK.getId(), Color.BLACK.getCode(), Color.class, 0),
    WHITE(Color.WHITE.getId(), Color.WHITE.getCode(), Color.class, 0),
    RED(Color.RED.getId(), Color.RED.getCode(), Color.class, 0),
    BLUE(Color.BLUE.getId(), Color.BLUE.getCode(), Color.class, 0),
    BROWN(Color.BROWN.getId(), Color.BROWN.getCode(), Color.class, 0),
    PINK(Color.PINK.getId(), Color.PINK.getCode(), Color.class, 0),
    SILVER(Color.SILVER.getId(), Color.SILVER.getCode(), Color.class, 0),
    GREY(Color.GREY.getId(), Color.GREY.getCode(), Color.class, 0),
    GREEN(Color.GREEN.getId(), Color.GREEN.getCode(), Color.class, 0),
    GOLD(Color.GOLD.getId(), Color.GOLD.getCode(), Color.class, 0),
    PURPLE(Color.PURPLE.getId(), Color.PURPLE.getCode(), Color.class, 0),
    BEIGE(Color.BEIGE.getId(), Color.BEIGE.getCode(), Color.class, 0),
    MEN(Gender.MEN.getId(), Gender.MEN.getCode(), Gender.class, 1),
    WOMEN(Gender.WOMEN.getId(), Gender.WOMEN.getCode(), Gender.class, 1),
    UNISEX(Gender.UNISEX.getId(), Gender.UNISEX.getCode(), Gender.class, 1),
    CHILDREN(Gender.CHILDREN.getId(), Gender.CHILDREN.getCode(), Gender.class, 1);

    private final Integer id;
    private final String code;
    private final Class type;
    private final Integer groupId;//разделяем атрибуты на группы для корректного поиска

    public static AttributeEnum findById(Integer id) {
        if (id != null) {
            AttributeEnum[] attributes = AttributeEnum.values();
            for (int i = 0; i < attributes.length; i++) {
                if (attributes[i].getId().equals(id)) {
                    return attributes[i];
                }
            }
        }
        return null;
    }

    public static AttributeEnum findByCode(String code) {
        if (!StringUtils.isEmpty(code)) {
            AttributeEnum[] attributes = AttributeEnum.values();
            for (int i = 0; i < attributes.length; i++) {
                if (attributes[i].getCode().equals(code)) {
                    return attributes[i];
                }
            }
        }
        return null;
    }


    //    private Set<Attribute> getAttributesEnumSetByAttrStrSet(Set<String> attributes){
//        HashSet<Attribute> result =new HashSet();
//        for(String attr: attributes){
//            Attribute attribute = lookupByCode(attr);
//            if(attribute != null){
//                result.add(attribute);
//            }
//        }
//        return result;
//    }
//
//    public static Attribute lookupByCode(String code) {
//        if(code != null) {
//            Attribute[] attributes = Attribute.values();
//            for (int i = 0; i < attributes.length; i++) {
//                if (attributes[i].getCode().equals(code)) {
//                    return attributes[i];
//                }
//            }
//        }
//        return null;
//    }
}