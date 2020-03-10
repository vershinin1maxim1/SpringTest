package com.example.sweater.domain;


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
    public Page<Product> getPageByParams(Collection<List<Integer>>  attributeIds, Pageable pageable){
        List<Product> products = findByParams(attributeIds, pageable);
        long count = countByParams(attributeIds);
        Page page=new PageImpl(products, pageable, count);
        return page;
    }

    public List<Product> findByParams(Collection<List<Integer>>  attributeIds, Pageable pageable){
        CriteriaQuery<Product> query =entityManager.getCriteriaBuilder().createQuery(Product.class);
        Root<Product> selection = query.from(Product.class);
        query.select(selection);
        Predicate wherePredicate = makeWhere(selection, query, attributeIds);
        query.where(wherePredicate);
//        List<Product> orderBy = getOrderBy(selection, fieldToSort, desc);
//        orderBy.add(idDescOrder);
//        query.orderBy(orderBy);
        //query.distinct(true);
//        return getResultList(query, quantity, startFrom);
        return entityManager.createQuery(query).setFirstResult(pageable.getPageNumber()).setMaxResults(pageable.getPageSize()).getResultList();

    }

    public long countByParams(Collection<List<Integer>>  attributeIds) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Product> selection = query.from(Product.class);
        query.select(criteriaBuilder.count(selection));
        Predicate wherePredicate = makeWhere(selection, query, attributeIds);
        query.where(wherePredicate);
        return entityManager.createQuery(query).getSingleResult();
    }

    private Predicate makeWhere(Root<Product> selection, CriteriaQuery query, Collection<List<Integer>> attributeIds){
        List<Predicate> allPredicates = new ArrayList<>();
//        ListJoin<Product, Attribute> attributesJoin = selection.join(selection.get("attributes"));
        List<Predicate> orAttributePredicates = new ArrayList<>();
        if (attributeIds!=null&&attributeIds.size()>0) {
            for(List<Integer> attributeId:attributeIds) {
                SetJoin<Product, Attribute> attributesJoin = selection.join(Product_.attributes);
                allPredicates.add(attributesJoin.get(Attribute_.attributeId).in(attributeId));
            }
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
}
