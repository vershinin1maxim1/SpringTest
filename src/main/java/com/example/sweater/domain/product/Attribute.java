package com.example.sweater.domain.product;

import java.util.HashSet;
import java.util.Set;

public enum Attribute {
    BLACK("chernyj", Color.class),
    WHITE("belyj", Color.class),
    RED("krasnyj", Color.class),
    MEN("muzhskoj", Gender.class),
    WOMEN("zhenskij", Gender.class);

    private final String code;
    private final Class type;

    private Attribute(String code, Class type) {
        this.code = code;
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    public String getCode() {
        return code;
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