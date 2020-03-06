package com.example.sweater.domain.dto;

//@Projection(name = "ProductProxyFilterDaoImpl", types = { Product.class })
public interface ProductProxyFilterDto {
//    @Value("#{target.name}")
    Integer getAttributeId();
//    @Value("#{target.price}")
    Long getCount();
}
