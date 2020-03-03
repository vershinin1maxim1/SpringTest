package com.example.sweater.domain.product;

import com.example.sweater.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.net.Inet4Address;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attribute {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "attribute_id")
    private Integer attributeId;

    public Attribute(Product product, Integer attributeId) {
        this.product=product;
        this.attributeId=attributeId;
    }

    public Attribute(Product product) {
        this.product=product;
    }
}
