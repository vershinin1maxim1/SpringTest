package com.example.sweater.repos;

import com.example.sweater.domain.dto.ProductProxyFilterDto;
import com.example.sweater.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {

    // погуглить PagingAndSortingRepository
    List<Product> findByName(String Name);
//     List<Product> findDistinctByAttributes(Set<Attribute> attributes);

    //    Integer find
    @Query(value = "SELECT MAX(p.price) FROM Product p")
    public Integer findMaxPrice();

    @Query(value = "SELECT MIN(p.price) FROM Product p")
    public Integer findMinPrice();

    @Query(value = "SELECT MAX(p.frame) FROM Product p")
    public Integer findMaxFrame();

    @Query(value = "SELECT MIN(p.frame) FROM Product p")
    public Integer findMinFrame();

    @Query(value = "SELECT DISTINCT p FROM Product p LEFT JOIN Attribute a ON a.product=p WHERE ((:attributeIds) IS NULL OR (a.attributeId IN (:attributeIds))) " +
            "AND (:minPrice IS NULL OR p.price>=:minPrice) AND (:maxPrice IS NULL OR p.price<=:maxPrice) " +
            "AND (:minFrame IS NULL OR p.frame>=:minFrame) AND (:maxFrame IS NULL OR p.frame<=:maxFrame)")
    public Page<Product> findByAttributes(@Param("attributeIds") Set<Integer> attributeIds, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice, @Param("minFrame")  Integer minFrame, @Param("maxFrame")  Integer maxFrame, Pageable pageable);

    @Query(value = "SELECT count(p) as count,a.attributeId as id FROM Product p JOIN Attribute a ON a.product=p GROUP BY a.attributeId")
    Map<Integer, Object> countOfAttributes();

}
