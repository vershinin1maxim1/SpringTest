package com.example.sweater.service;
import org.apache.log4j.Logger;
import com.example.sweater.domain.product.Product;
import com.example.sweater.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class SystemPropertiesService {
    private static final Logger LOG = Logger.getLogger(SystemPropertiesService.class);
    @Autowired
    private ProductRepo productRepo;
    //обновляет параметры фильтра.не знаю, как это будет работать на сервере и нормально ли это, нужно будет еще почитать, надо бы еще вызов в постконструкт запихать и посмотреть когда вызывается
    public void fillSystemProperties() {



        try(FileWriter writer = new FileWriter("target\\classes\\systemProperties.properties", false))
        {
            StringBuilder text=new StringBuilder();
            text.append("properties.maxPrice=").append(productRepo.findMaxPrice()).append("\n");
            text.append("properties.minPrice=").append(productRepo.findMinPrice()).append("\n");
            writer.append(text.toString());
            writer.flush();
        }
        catch(IOException ex){
            LOG.error("Не смог записать SystemProperties в файл конфигурации");
        }
    }
}
