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
    private Long vendor;
    private String description;
    private String name;
    private Integer price;
    private Integer frame;
    private String filename;
    private Set<Color> colors = new HashSet<>();
    private Set<Gender> genders = new HashSet<>();
    private Set<Material> materials = new HashSet<>();
    private Set<FrameForm> frameForms = new HashSet<>();
    private Brand brand;


    public ProductProxy(Product product) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.name = product.getName();
        this.filename = product.getFilename();
        this.price = product.getPrice();
        this.frame = product.getFrame();
        this.vendor = product.getVendor();
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
                    if (type.equals(Material.class)) {
                        this.materials.add(Material.valueOf(attrEnum.toString()));
                    }
                    if (type.equals(FrameForm.class)) {
                        this.frameForms.add(FrameForm.valueOf(attrEnum.toString()));
                    }
                    if (type.equals(Brand.class)) {
                        this.brand=(Brand.valueOf(attrEnum.toString()));
                    }
                }
            }
        }
    }
}
