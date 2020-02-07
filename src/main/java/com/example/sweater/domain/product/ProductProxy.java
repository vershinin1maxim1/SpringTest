package com.example.sweater.domain.product;

import java.util.HashSet;
import java.util.Set;

public class ProductProxy {
    private Long id;
    private String description;
    private String name;
    private String filename;
    private Set<Color> colors = new HashSet<>();
    private Set<Gender> genders = new HashSet<>();

    public ProductProxy() {
    }

    public ProductProxy(Product product) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.name = product.getName();
        this.filename = product.getFilename();
        for (Attribute attr : product.getAttributes()) {
            Class type = attr.getType();
            if (type.equals(Color.class)) {
                this.colors.add(Color.valueOf(attr.toString()));
                continue;
            }
            if (type.equals(Gender.class)) {
                this.genders.add(Gender.valueOf(attr.toString()));
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public Set<Gender> getGenders() {
        return genders;
    }

    public void setGenders(Set<Gender> genders) {
        this.genders = genders;
    }
}
