package com.glassesShop.test;

import com.glassesShop.domain.product.Attribute;
import com.glassesShop.domain.product.AttributeEnum;
import com.glassesShop.domain.product.Product;
import com.glassesShop.repos.ProductRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

    @Autowired
    private ProductRepo productRepo;


    @Test
    public void fillProducts() {
        int count=3;
        for(int i=0;i< count;i++) {
            createProduct(i);
        }
    }

    public void createProduct(int i){
        Product product = new Product();
        product.setName("Наименование товара"+i);
        product.setDescription("Описание товара Описание товара Описание товара Описание товара Описание товара Описание товара Описание товара " +
                "Описание товара Описание товара Описание товара Описание товара Описание товара Описание товара Описание товара "+i);
        product.setPrice(1000+i);
        Set<Attribute> attributes = new HashSet<>();
        AttributeEnum attributeEnum = AttributeEnum.BURBERRY;
        Attribute attribute = new Attribute(product, attributeEnum);
        attributes.add(attribute);
        product.setAttributes(attributes);
        product.setVendor((long) (2000 + i));
        product.setFrame(200+i);
        productRepo.save(product);
    }
}