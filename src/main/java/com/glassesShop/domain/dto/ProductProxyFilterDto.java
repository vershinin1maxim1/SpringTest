package com.glassesShop.domain.dto;

import lombok.Value;

//@Projection(name = "ProductProxyFilterDaoImpl", types = { Product.class })
@Value
public class ProductProxyFilterDto {
//    @Value("#{target.name}")
    private Integer attributeId;
//    @Value("#{target.price}")
    private Long count;
}
