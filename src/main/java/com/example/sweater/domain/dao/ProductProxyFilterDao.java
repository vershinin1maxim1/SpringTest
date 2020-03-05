package com.example.sweater.domain.dao;


import com.example.sweater.domain.dto.ProductProxyFilterDto;
import com.example.sweater.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductProxyFilterDao extends JpaRepository<Product, Long> {



    @Query(value = "SELECT a.attributeId as attributeId,  count(p) as count FROM Product p JOIN Attribute a ON a.product=p GROUP BY a.attributeId")
//    @Query(value = "SELECT new ProductProxyFilterDto(a.id, count(p)) FROM Product p JOIN Attribute a ON a.product=p GROUP BY a.attributeId")
//    @Query(value = "SELECT p.name as name,  p.price as price FROM Product p", nativeQuery = true)
//    @Query(value = "SELECT p.name as name, p.price as price FROM Product p")
    List<ProductProxyFilterDto> countOfAttributes();
//    ProductProxyFilterDto findFirstByPrice(Integer firstName);
}

