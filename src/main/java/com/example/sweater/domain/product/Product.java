package com.example.sweater.domain.product;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Заполните описание")
    @Length(max = 2048, message = "Описание слишком длинное")
    private String description;
    @Length(max = 255, message = "Имя слишком длинное")
    private String name;

    private Integer price;//цена

    private Integer frame;//размер рамки

    private String filename;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Attribute> attributes;

//    @ElementCollection(targetClass = AttributeEnum.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "attributes", joinColumns = @JoinColumn(name = "product_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<AttributeEnum> attributes;

    public Product() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFrame() {
        return frame;
    }

    public void setFrame(Integer frame) {
        this.frame = frame;
    }
}
