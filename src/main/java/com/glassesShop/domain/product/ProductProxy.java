package com.glassesShop.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class ProductProxy {
    private Long id;
    private String description;
    private String name;
    private Integer price;
    private Integer frame;
    private String filename;
    private Set<Color> colors = new HashSet<>();
    private Set<Gender> genders = new HashSet<>();
    private Set<FrameForm> frameForms = new HashSet<>();
    private Set<Brand> brands = new HashSet<>();


    public ProductProxy(Product product) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.name = product.getName();
        this.filename = product.getFilename();
        this.price = product.getPrice();
        this.frame = product.getFrame();
        if(product.getAttributes()!=null) {
            for (Attribute attr : product.getAttributes()) {
                AttributeEnum attrEnum = AttributeEnum.findById(attr.getAttributeId());
                if (attrEnum != null) {
                    Class type = attrEnum.getType();
                    if (type.equals(Color.class)) {
                        this.colors.add(Color.valueOf(attrEnum.toString()));
                        continue;
                    }
                    if (type.equals(Gender.class)) {
                        this.genders.add(Gender.valueOf(attrEnum.toString()));
                    }
                    if (type.equals(FrameForm.class)) {
                        this.frameForms.add(FrameForm.valueOf(attrEnum.toString()));
                    }
                    if (type.equals(Brand.class)) {
                        this.brands.add(Brand.valueOf(attrEnum.toString()));
                    }
                }
            }
        }
    }
}
