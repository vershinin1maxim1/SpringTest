package com.glassesShop.repos;

import com.glassesShop.domain.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {
//static final String WHERE_CONDITION = " ((:attributeIds) IS NULL OR (a.attributeId IN (:attributeIds))) " +
//            "AND (:minPrice IS NULL OR p.price>=:minPrice) AND (:maxPrice IS NULL OR p.price<=:maxPrice) " +
//            "AND (:minFrame IS NULL OR p.frame>=:minFrame) AND (:maxFrame IS NULL OR p.frame<=:maxFrame) " +
//            "AND ((:groupIds) IS NULL OR (a.groupId IN (:groupIds)))";
//    // погуглить PagingAndSortingRepository
//    List<Product> findByName(String Name);
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

//    @Query(value = "SELECT DISTINCT p FROM Product p LEFT JOIN Attribute a ON a.product=p WHERE"+WHERE_CONDITION)
//    public Page<Product> findByAttributes(@Param("attributeIds") Set<Integer> attributeIds, @Param("groupIds") Set<Integer> groupIds, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice, @Param("minFrame")  Integer minFrame, @Param("maxFrame")  Integer maxFrame, Pageable pageable);
//
//
//    @Query(value = "SELECT a.attributeId as attributeId,  count(p) as count FROM Product p LEFT JOIN Attribute a ON a.product=p WHERE" + WHERE_CONDITION + "GROUP BY a.attributeId")
//    List<ProductProxyFilterDto> countOfAttributesByParam(@Param("attributeIds") Set<Integer> attributeIds, @Param("groupIds") Set<Integer> groupIds, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice, @Param("minFrame")  Integer minFrame, @Param("maxFrame")  Integer maxFrame);

//    @Query(value = "SELECT -1 as attributeId, count(p) as count FROM Product p LEFT JOIN Attribute a ON a.product=p WHERE" + WHERE_CONDITION)
//    ProductProxyFilterDto countAllByParam(@Param("attributeIds") Set<Integer> attributeIds, @Param("groupIds") Set<Integer> groupIds, @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice, @Param("minFrame")  Integer minFrame, @Param("maxFrame")  Integer maxFrame);

}
