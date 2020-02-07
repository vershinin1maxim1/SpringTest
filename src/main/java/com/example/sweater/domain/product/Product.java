package com.example.sweater.domain.product;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    private String filename;

    @ElementCollection(targetClass = Attribute.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "attributes", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private Set<Attribute> attributes;

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

    public void setAttributes(Set<Attribute> colors) {
        this.attributes = colors;
    }
}
