package com.example.sweater.domain.dao;


import com.example.sweater.domain.dto.ProductProxyFilterDto;
import com.example.sweater.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProductProxyFilterDao extends JpaRepository<Product, Long> {




//    ProductProxyFilterDto findFirstByPrice(Integer firstName);


//
//    @Query(value = "SELECT DISTINCT p FROM Product p LEFT JOIN Attribute a ON a.product=p WHERE ((:attributeIds) IS NULL OR (a.attributeId IN (:attributeIds))) " +
//            "AND (:minPrice IS NULL OR p.price>=:minPrice) AND (:maxPrice IS NULL OR p.price<=:maxPrice) " +
//            "AND (:minFrame IS NULL OR p.frame>=:minFrame) AND (:maxFrame IS NULL OR p.frame<=:maxFrame)")
//    public Page<Product> findByAttributes(@Param("attributeIds") Set<Integer> attributeIds, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice, @Param("minFrame")  Integer minFrame, @Param("maxFrame")  Integer maxFrame, Pageable pageable);

}

