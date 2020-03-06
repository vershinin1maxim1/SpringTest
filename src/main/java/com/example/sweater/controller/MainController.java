package com.example.sweater.controller;

import com.example.sweater.domain.SystemPropertiesConfig;
import com.example.sweater.domain.dto.ProductProxyFilterDto;
import com.example.sweater.domain.product.*;
import com.example.sweater.repos.ProductRepo;
import com.example.sweater.service.SystemPropertiesService;
import lombok.extern.log4j.Log4j;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;
@Log4j
@Controller
public class MainController {
    private static final int productsOnPage = 3;
    @Autowired
    private ProductRepo productRepo;
//    @Autowired
//    private ProductProxyFilterRepo productProxyFilterRepo;
    @Autowired
    private SystemPropertiesConfig systemPropertiesConfig;
    @Autowired
    private SystemPropertiesService systemPropertiesService;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping({"/ochkiGetActualFilter", "/ochkiGetActualFilter/**"})
    @ResponseBody
    public  List<ProductProxyFilterDto>  getActualFilter(@RequestParam(required = false, defaultValue = "") String filter, HttpServletRequest request) {
        Set<Integer> attributesIds = null;
        Integer minPrice=null;
        Integer maxPrice=null;
        Integer maxFrame=null;
        Integer minFrame=null;
        String[] codes = splitUrl(request, "/ochkiGetActualFilter");
        if(codes!=null&&codes.length>0){
            ArrayList<String> paramList =new ArrayList<>(Arrays.asList(codes));
            ListIterator<String> paramIterator= paramList.listIterator();
            if(StringUtils.isEmpty( paramIterator.next())) {
                paramIterator.remove();
            }


            if (paramIterator.hasNext()) {
                if ("price".equals(paramIterator.next())) {
                    paramIterator.remove();
                    if (paramIterator.hasNext()) {
                        String[] split = paramIterator.next().split("-");
                        paramIterator.remove();
                        if (split.length == 2) {
                            minPrice = Integer.valueOf(split[0]);
                            maxPrice = Integer.valueOf(split[1]);
                        }
                    }
                } else {
                    paramIterator.previous();
                }
            }

            if (paramIterator.hasNext()) {
                if ("razmer_ramki".equals(paramIterator.next())) {
                    paramIterator.remove();
                    if (paramIterator.hasNext()) {
                        String[] split = paramIterator.next().split("-");
                        paramIterator.remove();
                        if (split.length == 2) {
                            minFrame = Integer.parseInt(split[0]);
                            maxFrame = Integer.parseInt(split[1]);
                        }
                    }
                } else {
                    paramIterator.previous();
                }
            }
            //тут надо доделать
            attributesIds = getAttributesIds(paramIterator);
        }
        Sort sortedBy = Sort.unsorted();
        List<ProductProxyFilterDto>  productProxyFilters = productRepo.countOfAttributesByParam(attributesIds, minPrice, maxPrice, minFrame, maxFrame);
        productProxyFilters.add(productRepo.countAllByParam(attributesIds, minPrice, maxPrice, minFrame, maxFrame));
        return productProxyFilters;
    }

    @GetMapping({"/ochki","/ochki/**"})
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       @RequestParam(required = false, defaultValue = "") String sort,
                       @RequestParam(required = false, defaultValue = "") String order,
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       Model model,
                       HttpServletRequest request) {
        log.warn("ТЕСТОВОЕ СООБЩЕНИЕ");
//        systemPropertiesService.fillSystemProperties();//убрать это отсюда.возможно сделать постконструкт, проверять при добавлении товаров
        Page<Product> products;
        Set<Integer> attributesIds = null;
        Integer minPrice=null;
        Integer maxPrice=null;
        Integer maxFrame=null;
        Integer minFrame=null;
        Product filterProduct = new Product();
        String[] codes = splitUrl(request, "/ochki");
        if(codes!=null&&codes.length>0){
           ArrayList<String> paramList =new ArrayList<>(Arrays.asList(codes));
            ListIterator<String> paramIterator= paramList.listIterator();
            if(StringUtils.isEmpty( paramIterator.next())) {
                paramIterator.remove();
        }


            if (paramIterator.hasNext()) {
                if ("price".equals(paramIterator.next())) {
                    paramIterator.remove();
                    if (paramIterator.hasNext()) {
                        String[] split = paramIterator.next().split("-");
                        paramIterator.remove();
                        if (split.length == 2) {
                            minPrice = Integer.valueOf(split[0]);
                            maxPrice = Integer.valueOf(split[1]);
                        }
                    }
                } else {
                    paramIterator.previous();
                }
            }

            if (paramIterator.hasNext()) {
                if ("razmer_ramki".equals(paramIterator.next())) {
                    paramIterator.remove();
                    if (paramIterator.hasNext()) {
                        String[] split = paramIterator.next().split("-");
                        paramIterator.remove();
                        if (split.length == 2) {
                            minFrame = Integer.parseInt(split[0]);
                            maxFrame = Integer.parseInt(split[1]);
                        }
                    }
                } else {
                    paramIterator.previous();
                }
            }


                //тут надо доделать

       attributesIds = getAttributesIds(paramIterator);
        }
        Sort sortedBy = Sort.unsorted();
//        String sort="";
//        String order="";
        if(!StringUtils.isEmpty(sort)) {

            sortedBy = "DESC".equals(order) ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        }
        Pageable pageable = PageRequest.of(page-1, productsOnPage, sortedBy);
        products = productRepo.findByAttributes(attributesIds, minPrice, maxPrice, minFrame, maxFrame, pageable);

        if (attributesIds!=null &&attributesIds.size()>0) {
            Set<Attribute> attributes = attributesIds.stream().map(s -> new Attribute(filterProduct, s)).collect(Collectors.toSet());
            filterProduct.setAttributes(attributes);
        }

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
        model.addAttribute("filterMaxFrame", systemPropertiesConfig.getMaxFrame());
        model.addAttribute("filterMinFrame", systemPropertiesConfig.getMinFrame());
        model.addAttribute("setFilterMaxPrice", maxPrice==null?systemPropertiesConfig.getMaxPrice():maxPrice);
        model.addAttribute("setFilterMinPrice", minPrice==null?systemPropertiesConfig.getMinPrice():minPrice);
        model.addAttribute("setFilterMaxFrame", maxFrame==null?systemPropertiesConfig.getMaxFrame():maxFrame);
        model.addAttribute("setFilterMinFrame", minFrame==null?systemPropertiesConfig.getMinFrame():minFrame);


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
    private Set<Integer> getAttributesIds(ListIterator<String> codes){

        Set<Integer> result = new HashSet<>();
            while (codes.hasNext()) {
                String code =codes.next();
                    AttributeEnum attributeEnumByCode = AttributeEnum.findByCode(code);
                    if(attributeEnumByCode!=null) {
                        result.add(attributeEnumByCode.getId());
                    }
            }
            if(result.size()==0){
                return null;
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
