package com.glassesShop.repos;

import com.glassesShop.domain.product.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepo extends JpaRepository<Attribute, Long> {
}
