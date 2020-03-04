package com.example.sweater.controller;

import com.example.sweater.domain.User;
import com.example.sweater.domain.product.*;
import com.example.sweater.service.ProductService;
import com.example.sweater.service.SystemPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class AdminPageController {
    @Autowired
    private SystemPropertiesService systemPropertiesService;
    @Autowired
    private ProductService productService;

    @GetMapping("/user-products/edit")
    public String userProducts(
            @AuthenticationPrincipal User currentUser,
            Model model,
            @RequestParam(required = false) Product product
    ) {
//        Iterable<Product> products = productRepo.findAll();
//        model.addAttribute("products", products);
        model.addAttribute("product", product==null?null:new ProductProxy(product));
        model.addAttribute("colors", Color.values());
        model.addAttribute("genders", Gender.values());
        return "userProducts";
    }


    @PostMapping("/user-products/edit")
    public String updateProduct(
            @AuthenticationPrincipal User currentUser,
            @RequestParam("id") Product product,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file,
            @RequestParam Map<String, String> form
    ) throws IOException {
        systemPropertiesService.fillSystemProperties();//убрать это отсюда.возможно сделать постконструкт, проверять при добавлении товаров
        productService.saveProduct(product, name, description, file, form);
        return "redirect:/user-products/edit";
    }
}
