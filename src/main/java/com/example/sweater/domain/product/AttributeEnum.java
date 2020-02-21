package com.example.sweater.domain.product;

import org.springframework.util.StringUtils;

public enum AttributeEnum {
    BLACK(0, Color.BLACK.getCode(), Color.class),
    WHITE(1, Color.WHITE.getCode(), Color.class),
    RED(2, Color.RED.getCode(), Color.class),
    MEN(3, Gender.MEN.getCode(), Gender.class),
    WOMEN(4, Gender.WOMEN.getCode(), Gender.class);

    private final Integer id;
    private final String code;
    private final Class type;

    private AttributeEnum(Integer id, String code, Class type) {
        this.id = id;
        this.code = code;
        this.type = type;

    }

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

    public Class getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public Integer getId() {
        return id;
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