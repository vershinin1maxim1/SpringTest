package com.example.sweater.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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


}
