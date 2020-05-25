package com.glassesShop.controller;

import com.glassesShop.domain.User;
import com.glassesShop.domain.product.*;
import com.glassesShop.service.ProductService;
import com.glassesShop.service.SystemPropertiesService;
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

    @GetMapping("/admin-products/edit")
    public String adminProducts(
            @AuthenticationPrincipal User currentUser,
            Model model,
            @RequestParam(required = false) Product product
    ) {
//        Iterable<Product> products = productRepo.findAll();
//        model.addAttribute("products", products);
        model.addAttribute("product", product==null?null:new ProductProxy(product));
        model.addAttribute("colors", Color.values());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("materials", Material.values());
        model.addAttribute("frameForms", FrameForm.values());
        model.addAttribute("brands", Brand.values());
        return "adminProducts";
    }


    @PostMapping("/admin-products/edit")
    public String updateProduct(
            @AuthenticationPrincipal User currentUser,
            @RequestParam("id") Product product,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file,
            @RequestParam Map<String, String> form
    ) throws IOException {
//        systemPropertiesService.fillSystemProperties();//убрать это отсюда.возможно сделать постконструкт, проверять при добавлении товаров
        productService.saveProduct(product, name, description, file, form);
        return "redirect:/admin-products/edit";
    }
}
