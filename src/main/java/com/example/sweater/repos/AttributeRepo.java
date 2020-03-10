package com.example.sweater.repos;

import com.example.sweater.domain.dto.ProductProxyFilterDto;
import com.example.sweater.domain.product.Attribute;
import com.example.sweater.domain.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface AttributeRepo extends JpaRepository<Attribute, Long> {
}
