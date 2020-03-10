package com.example.sweater.domain;


import com.example.sweater.domain.dto.ProductProxyFilterDto;
import com.example.sweater.domain.product.Attribute;
import com.example.sweater.domain.product.Attribute_;
import com.example.sweater.domain.product.Product;
import com.example.sweater.domain.product.Product_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.*;

@Repository
public class ProductDAO {

    @Autowired
    private EntityManager entityManager;
    public Page<Product> getPageByParams(Collection<List<Integer>>  attributeIds, Integer minPrice, Integer maxPrice,  Integer minFrame, Integer maxFrame, Pageable pageable, String sort, boolean desc){
        List<Product> products = findByParams(attributeIds, minPrice, maxPrice,  minFrame, maxFrame, pageable, sort, desc);
        long count = countByParams(attributeIds, minPrice, maxPrice,  minFrame, maxFrame);
        Page page=new PageImpl(products, pageable, count);
        return page;
    }

    public List<Product> findByParams(Collection<List<Integer>>  attributeIds, Integer minPrice, Integer maxPrice,  Integer minFrame, Integer maxFrame, Pageable pageable, String sort, boolean desc){
        CriteriaQuery<Product> query =entityManager.getCriteriaBuilder().createQuery(Product.class);
        Root<Product> selection = query.from(Product.class);
        query.select(selection);
        Predicate wherePredicate = makeWhere(selection, query, attributeIds, minPrice, maxPrice,  minFrame, maxFrame);
        query.where(wherePredicate);
        if(!StringUtils.isEmpty(sort)) {
            query.orderBy(getOrderBy(selection, sort, desc));
        }

        //query.distinct(true);
//        return getResultList(query, quantity, startFrom);
        return entityManager.createQuery(query).setFirstResult(pageable.getPageNumber()).setMaxResults(pageable.getPageSize()).getResultList();

    }

    public long countByParams(Collection<List<Integer>>  attributeIds, Integer minPrice, Integer maxPrice,  Integer minFrame, Integer maxFrame) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Product> selection = query.from(Product.class);
        query.select(criteriaBuilder.count(selection));
        Predicate wherePredicate = makeWhere(selection, query, attributeIds, minPrice, maxPrice,  minFrame, maxFrame);
        query.where(wherePredicate);
        return entityManager.createQuery(query).getSingleResult();
    }

    public List<ProductProxyFilterDto> countOfAttributesByParam(Collection<List<Integer>>  attributeIds, Integer minPrice, Integer maxPrice,  Integer minFrame, Integer maxFrame){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductProxyFilterDto> query = criteriaBuilder.createQuery(ProductProxyFilterDto.class);
        Root<Product> selection = query.from(Product.class);
        SetJoin<Product, Attribute> attributesJoin = selection.join(Product_.attributes, JoinType.LEFT);
//        a.attributeId as attributeId,  count(p) as count
        query.select(criteriaBuilder.construct(ProductProxyFilterDto.class,attributesJoin.get(Attribute_.attributeId), criteriaBuilder.count(selection.get(Product_.id)).alias("count")));
        Predicate wherePredicate = makeWhere(selection, query, attributeIds, minPrice, maxPrice,  minFrame, maxFrame);
        query.where(wherePredicate);
        query.groupBy(attributesJoin.get(Attribute_.attributeId));
        return entityManager.createQuery(query).getResultList();
    }

    private Predicate makeWhere(Root<Product> selection, CriteriaQuery query, Collection<List<Integer>> attributeIds, Integer minPrice, Integer maxPrice,  Integer minFrame, Integer maxFrame){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        List<Predicate> allPredicates = new ArrayList<>();
        List<Predicate> orAttributePredicates = new ArrayList<>();
        if (attributeIds!=null&&attributeIds.size()>0) {
            for(List<Integer> attributeId:attributeIds) {
                SetJoin<Product, Attribute> attributesJoin = selection.join(Product_.attributes);
                allPredicates.add(attributesJoin.get(Attribute_.attributeId).in(attributeId));
            }
        }
        if (minPrice!=null) {
            allPredicates.add(criteriaBuilder.greaterThanOrEqualTo(selection.get(Product_.price), minPrice));
        }
        if (maxPrice!=null) {
            allPredicates.add(criteriaBuilder.lessThanOrEqualTo(selection.get(Product_.price), maxPrice));
        }
        if (minFrame!=null) {
            allPredicates.add(criteriaBuilder.greaterThanOrEqualTo(selection.get(Product_.frame), minFrame));
        }
        if (maxFrame!=null) {
            allPredicates.add(criteriaBuilder.lessThanOrEqualTo(selection.get(Product_.frame), maxFrame));
        }
        return unionWithAndOperation(allPredicates);
    }


    /**
     * @param predicates коллекция предикатов
     * @return предикат, содержащий все предикаты из коллекции с операций И между ними
     */
    protected Predicate unionWithAndOperation(Collection<Predicate> predicates) {
        if (predicates == null) {
            throw new IllegalArgumentException("Params can't be null");
        }
        return entityManager.getCriteriaBuilder().and(predicates.toArray(new Predicate[predicates.size()]));
    }

    /**
     * @param predicates коллекция предикатов
     * @return предикат, содержащий все предикаты из коллекции с операций И между ними
     */
    protected Predicate unionWithOrOperation(Collection<Predicate> predicates) {
        if (predicates == null) {
            throw new IllegalArgumentException("Params can't be null");
        }
        return entityManager.getCriteriaBuilder().or(predicates.toArray(new Predicate[predicates.size()]));
    }

    public Predicate equal(Expression<?> x, Object y) {
        return entityManager.getCriteriaBuilder().equal(x, y);
    }

    protected List<Order> getOrderBy(From selection, String fieldToSort, boolean isDesc) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        if (StringUtils.isEmpty(fieldToSort)) {
            throw new IllegalArgumentException("Field to sort is empty");
        }
        String[] orderClauses = fieldToSort.split(", ");

        List<Order> result = new ArrayList<>();
        for (String clause : orderClauses) {
            Path sortPath = getField(selection, clause);
            Order order = isDesc(isDesc, clause) ? criteriaBuilder.desc(sortPath) : criteriaBuilder.asc(sortPath);
            result.add(order);
        }
        return result;
    }


    private boolean isDesc(boolean isDesc, String clause) {
        if (clause.endsWith("desc")) {
            return true;
        } else if (clause.endsWith("asc")) {
            return false;
        } else {
            return isDesc;
        }
    }

    protected Path getField(From selection, String fieldToSort) {
        if (StringUtils.isEmpty(fieldToSort)) {
            throw new IllegalArgumentException("Field to sort is empty");
        }
        fieldToSort = removeSpaces(fieldToSort);

        String[] fields = fieldToSort.split("\\.");
        if (fields.length > 1) {
            Join sortFieldPath = selection.join(fields[0], JoinType.LEFT);
            for (int i = 1; i < fields.length - 1; i++) {
                sortFieldPath = sortFieldPath.join(fields[i], JoinType.LEFT);
            }
            return sortFieldPath.get(fields[fields.length - 1]);
        } else {
            return selection.get(fields[0]);
        }
    }

    private String removeSpaces(String fieldToSort) {
        int spacePosition = fieldToSort.indexOf(" ");
        if (spacePosition > 0) {
            return fieldToSort.substring(0, spacePosition);
        }
        return fieldToSort;
    }
}
