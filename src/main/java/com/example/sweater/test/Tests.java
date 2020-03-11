package com.example.sweater.test;

import com.example.sweater.domain.product.Product;
import com.example.sweater.repos.ProductRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests {

    @Autowired
    private ProductRepo productRepo;


    @Test
    public void fillProducts() {
        for(int i=0;i< 40;i++) {
            createProduct(i);
        }
    }

    public void createProduct(int i){
        Product product = new Product();
        product.setName("Наименование товара"+i);
        product.setDescription("Описание товара Описание товара Описание товара Описание товара Описание товара Описание товара Описание товара " +
                "Описание товара Описание товара Описание товара Описание товара Описание товара Описание товара Описание товара "+i);
        product.setPrice(1000+i);
        product.setFrame(200+i);
        productRepo.save(product);
    }
}