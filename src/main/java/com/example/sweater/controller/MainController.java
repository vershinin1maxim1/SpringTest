package com.example.sweater.controller;

import com.example.sweater.domain.SystemPropertiesConfig;
import com.example.sweater.domain.product.*;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.service.SystemPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Controller
public class MainController {
    private static final int productsOnPage = 3;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SystemPropertiesConfig systemPropertiesConfig;
    @Autowired
    private SystemPropertiesService systemPropertiesService;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping({"/ochki","/ochki/**"})
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       @RequestParam(required = false, defaultValue = "") String sort,
                       @RequestParam(required = false, defaultValue = "") String order,
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       Model model,
                       HttpServletRequest request) {
        Page<Product> products;
        String[] codes = splitUrl(request, "/ochki");
        //тут надо доделать
        Set<Integer> attributesIds = getAttributesIds(codes);

        Sort sortedBy = Sort.unsorted();
//        String sort="";
//        String order="";
        if(!StringUtils.isEmpty(sort)) {

            sortedBy = "DESC".equals(order) ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        }
        Pageable pageable = PageRequest.of(page-1, productsOnPage, sortedBy);
        if (attributesIds!=null &&attributesIds.size()>0) {
            products = productRepo.findByAttributes(attributesIds, pageable);
        } else {
            products = productRepo.findAll(pageable);
        }
        Product filterProduct = new Product();
        Set<Attribute> attributes = attributesIds.stream().map(s->new Attribute(filterProduct ,s)).collect(Collectors.toSet());
        filterProduct.setAttributes(attributes);
        ArrayList<ProductProxy> productProxys= new ArrayList();
        products.forEach(s->productProxys.add(new ProductProxy(s)));
        model.addAttribute("products", productProxys);
        //передадим искусственно созданный продукт со всемы изаполненными значениями фильтрации для заполнения фильтра
        model.addAttribute("filterProduct", new ProductProxy(filterProduct));
        model.addAttribute("filter", filter);//ненужно,удалить здесь и сверху
        model.addAttribute("colors", Color.values());
        model.addAttribute("genders", Gender.values());
        int currentPage = products.getNumber() + 1;
        int totalPages = products.getTotalPages();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("maxPage", max(currentPage, totalPages));
        model.addAttribute("minPage", min(currentPage, 1));
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("orderName", "name".equals(sort)?order:null);
        model.addAttribute("orderPrice", "price".equals(sort)?order:null);

        model.addAttribute("filterMaxPrice", systemPropertiesConfig.getMaxPrice());
        model.addAttribute("filterMinPrice", systemPropertiesConfig.getMinPrice());


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
    private Set<Integer> getAttributesIds(String[] codes){

        Set<Integer> result = new HashSet<>();
        if(codes!=null) {
            for (String code : codes) {
                if (!StringUtils.isEmpty(code)) {
                    AttributeEnum attributeEnumByCode = AttributeEnum.findByCode(code);
                    if(attributeEnumByCode!=null) {
                        result.add(attributeEnumByCode.getId());
                    }
                }
            }
        }
        return result;
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


}
