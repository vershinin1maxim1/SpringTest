package com.example.sweater.domain.product;

import com.example.sweater.domain.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.net.Inet4Address;
import java.util.Set;

@Entity
public class Attribute {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "attribute_id")
    private Integer attributeId;

    public Attribute() {
    }

    public Attribute(Product product, Integer attributeId) {
        this.product=product;
        this.attributeId=attributeId;
    }

    public Attribute(Product product) {
        this.product=product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }
}
