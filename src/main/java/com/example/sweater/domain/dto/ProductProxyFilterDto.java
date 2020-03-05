package com.example.sweater.domain.dto;

import com.example.sweater.domain.product.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

//@Projection(name = "ProductProxyFilterDaoImpl", types = { Product.class })
public interface ProductProxyFilterDto {
//    @Value("#{target.name}")
    Integer getAttributeId();
//    @Value("#{target.price}")
    Long getCount();
}
