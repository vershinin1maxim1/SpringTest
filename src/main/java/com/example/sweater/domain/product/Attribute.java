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
import java.util.Objects;
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
    @Column(name = "group_id")
    private Integer groupId;

    public Attribute(Product product, AttributeEnum attributeEnum) {
        this.product=product;
        this.attributeId=attributeEnum.getId();
        this.groupId=attributeEnum.getGroupId();
    }

    public Attribute(Product product) {
        this.product=product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return Objects.equals(product, attribute.product) &&
                Objects.equals(attributeId, attribute.attributeId) &&
                Objects.equals(groupId, attribute.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, attributeId, groupId);
    }
}
