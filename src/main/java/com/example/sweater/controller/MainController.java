package com.example.sweater.controller;

import com.example.sweater.domain.product.*;
import com.example.sweater.domain.User;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping({"/ochki","/ochki/**"})
    public String main(@RequestParam(required = false,
                    defaultValue = "") String filter, Model model, HttpServletRequest request) {
        Iterable<Product> products;
        String[] strings = splitUrl(request, "/ochki");
        //тут надо доделать
        if (!StringUtils.isEmpty(filter)) {
            products = productRepo.findByName(filter);
        } else {
            products = productRepo.findAll();
        }
        ArrayList<ProductProxy> productProxys= new ArrayList();
        products.forEach(s->productProxys.add(new ProductProxy(s)));
        model.addAttribute("products", productProxys);
        model.addAttribute("filter", filter);
        model.addAttribute("colors", Color.values());
        model.addAttribute("genders", Gender.values());
        return "main";
    }

    private String[] splitUrl(HttpServletRequest request, String startFrom){
        String referrer = request.getRequestURL().toString();
        String substring = referrer.substring(referrer.indexOf(startFrom)+startFrom.length());
        if(StringUtils.isEmpty(substring)){
            return null;
        }
        return substring.split("/");
    }


//    @PostMapping("/main")
//    public String add(
//            @Valid Product product,
//            BindingResult bindingResult,
//            Model model,
//            @RequestParam("file") MultipartFile file
//    ) throws IOException {
//
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//
//            model.mergeAttributes(errorsMap);
//            model.addAttribute("product", product);
//        } else {
//            productService.saveFile(product, file);
//
//            model.addAttribute("product", null);
//
//            productRepo.save(product);
//        }
//
//        Iterable<Product> products = productRepo.findAll();
//
//        model.addAttribute("products", products);
//
//        return "main";
//    }

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
        productService.saveProduct(product, name, description, file, form);
        return "redirect:/user-products/edit";
    }
}
