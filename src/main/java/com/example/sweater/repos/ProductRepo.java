package com.example.sweater.repos;

import com.example.sweater.domain.product.Attribute;
import com.example.sweater.domain.product.Color;
import com.example.sweater.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ProductRepo extends PagingAndSortingRepository<Product, Long> {
// погуглить PagingAndSortingRepository
    List<Product> findByName(String Name);
//     List<Product> findDistinctByAttributes(Set<Attribute> attributes);

    @Query(value = "SELECT DISTINCT p FROM Product p JOIN Attribute a ON a.product=p WHERE a.attributeId IN (:attributeIds)")
    public Page<Product> findByAttributes(@Param("attributeIds") Set<Integer> attributeIds,  Pageable pageable);//сюда добавить параметр , Pageable pageable А ВОЗВРАЩАЕТ Page<Product>
}
