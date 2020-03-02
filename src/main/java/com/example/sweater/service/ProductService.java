package com.example.sweater.service;

import com.example.sweater.domain.product.*;
import com.example.sweater.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    private ProductRepo productRepo;


    public void saveProduct(Product product, String name, String description, MultipartFile file, Map<String, String> form) throws IOException {
        if (product == null) {
            product = new Product();
        }
        if (!StringUtils.isEmpty(description)) {
            product.setDescription(description);
        }
        if (!StringUtils.isEmpty(name)) {
            product.setName(name);
        }
        if (file != null) {
            saveFile(product, file);
        }
        Set<String> attributes = Arrays.stream(AttributeEnum.values())
                .map(AttributeEnum::getCode)
                .collect(Collectors.toSet());
        if(product.getAttributes()==null){
            product.setAttributes(new HashSet<Attribute>());
        }
        product.getAttributes().clear();

        for (String key : form.keySet()) {
            if (attributes.contains(key)) {
                product.getAttributes().add(new Attribute(product, AttributeEnum.findByCode(key).getId()));
            }
        }
        productRepo.save(product);
    }

    public void saveFile(@Valid Product product, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            product.setFilename(resultFilename);
        }
    }
}
