package com.glassesShop.controller;

import com.glassesShop.domain.User;
import com.glassesShop.domain.product.*;
import com.glassesShop.service.ProductService;
import com.glassesShop.service.SystemPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Controller
public class BasketController {
    @Autowired
    private ProductService productService;

//    @GetMapping("/basket/add")
//    public String adminProducts(
//            Model model,
//            @RequestParam(required = false) Product product
//    ) {
////        Iterable<Product> products = productRepo.findAll();
////        model.addAttribute("products", products);
//        model.addAttribute("product", product==null?null:new ProductProxy(product));
//        model.addAttribute("colors", Color.values());
//        model.addAttribute("genders", Gender.values());
//        return "adminProducts";
//    }

    @GetMapping({"/korzina"})
    public String main(
                       Model model,
                       HttpServletRequest request) {

        return "basket";
    }

}
