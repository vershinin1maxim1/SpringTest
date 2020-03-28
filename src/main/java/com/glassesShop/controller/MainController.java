package com.glassesShop.controller;

import com.glassesShop.domain.ProductDAO;
import com.glassesShop.domain.SystemPropertiesConfig;
import com.glassesShop.domain.dto.ProductProxyFilterDto;
import com.glassesShop.domain.product.*;
import com.glassesShop.repos.ProductRepo;
import com.glassesShop.service.SystemPropertiesService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private static final int productsOnPage = 12;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SystemPropertiesConfig systemPropertiesConfig;
    @Autowired
    private SystemPropertiesService systemPropertiesService;
    @Autowired
    private ProductDAO productDAO;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping({"/ochkiGetActualFilter", "/ochkiGetActualFilter/**"})
    @ResponseBody
    public  List<ProductProxyFilterDto>  getActualFilter(@RequestParam(required = false, defaultValue = "") String filter, HttpServletRequest request) {
        Map<String,Integer> params=new HashMap<>();
        Set<AttributeEnum>  attributes =fillParams(request, "/ochkiGetActualFilter", params);
        Collection<List<Integer>>  attributeIds = groupAttributesByGroupId(attributes);
        List<ProductProxyFilterDto>  productProxyFilters = productDAO.countOfAttributesByParam(attributeIds, params.get("minPrice"), params.get("maxPrice"), params.get("minFrame"), params.get("maxFrame"));
        Long allCount = productDAO.countByParams(attributeIds, params.get("minPrice"), params.get("maxPrice"), params.get("minFrame"), params.get("maxFrame"));
        productProxyFilters.add(new ProductProxyFilterDto(-1, allCount));
        return productProxyFilters;
    }

    private Set<AttributeEnum> fillParams(HttpServletRequest request, String startUrl, Map<String,Integer> params){
        Set<AttributeEnum> attributes = new HashSet<>();
        Integer minPrice=null;
        Integer maxPrice=null;
        Integer minFrame=null;
        Integer maxFrame=null;
        String[] codes = splitUrl(request, startUrl);
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
            attributes = getAttributes(paramIterator);
        }
        params.put("minPrice", minPrice);
        params.put("maxPrice", maxPrice);
        params.put("minFrame", minFrame);
        params.put("maxFrame", maxFrame);
        return attributes;
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
        Product filterProduct = new Product();
        Map<String,Integer> params=new HashMap<>();
        Set<AttributeEnum>  attributes =fillParams(request, "/ochki", params);
        boolean desc="DESC".equals(order);
        Pageable pageable = PageRequest.of(page-1, productsOnPage);
        Collection<List<Integer>>  attributeIds = groupAttributesByGroupId(attributes);
        products = productDAO.getPageByParams(attributeIds, params.get("minPrice"), params.get("maxPrice"), params.get("minFrame"), params.get("maxFrame"), pageable, sort, desc);
        if (attributes!=null &&attributes.size()>0) {
            Set<Attribute> attributesDb = attributes.stream().map(s -> new Attribute(filterProduct, s)).collect(Collectors.toSet());
            filterProduct.setAttributes(attributesDb);
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
        model.addAttribute("setFilterMaxPrice", params.get("maxPrice")==null?systemPropertiesConfig.getMaxPrice():params.get("maxPrice"));
        model.addAttribute("setFilterMinPrice", params.get("minPrice")==null?systemPropertiesConfig.getMinPrice():params.get("minPrice"));
        model.addAttribute("setFilterMaxFrame", params.get("maxFrame")==null?systemPropertiesConfig.getMaxFrame():params.get("maxFrame"));
        model.addAttribute("setFilterMinFrame", params.get("minFrame")==null?systemPropertiesConfig.getMinFrame():params.get("minFrame"));


        return "main";
    }

    private Collection<List<Integer>>  groupAttributesByGroupId(Set<AttributeEnum> attributes) {
        Map<Integer, List<Integer>> mapGroupId = new HashMap<>();
        if(attributes==null){
            return null;
        }
        for (AttributeEnum attribute : attributes) {
            Integer groupId = attribute.getGroupId();
            List<Integer> attrValues = new ArrayList<>();
            if(mapGroupId.containsKey(groupId)){
                attrValues = mapGroupId.get(groupId);
            }
            attrValues.add(attribute.getId());
            mapGroupId.put(groupId, attrValues);
        }
        return mapGroupId.values();
    }

    private String[] splitUrl(HttpServletRequest request, String startFrom){
        String referrer = request.getRequestURL().toString();
        String substring = referrer.substring(referrer.indexOf(startFrom)+startFrom.length());
        if(StringUtils.isEmpty(substring)){
            return null;
        }
        return substring.split("/");
    }
    private Set<AttributeEnum> getAttributes(ListIterator<String> codes){

        Set<AttributeEnum> result = new HashSet<>();
            while (codes.hasNext()) {
                String code =codes.next();
                    AttributeEnum attributeEnumByCode = AttributeEnum.findByCode(code);
                    if(attributeEnumByCode!=null) {
                        result.add(attributeEnumByCode);
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
