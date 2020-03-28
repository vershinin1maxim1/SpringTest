package com.glassesShop.service;
import lombok.extern.log4j.Log4j;
import com.glassesShop.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
@Log4j
public class SystemPropertiesService {
    @Autowired
    private ProductRepo productRepo;
    //обновляет параметры фильтра.не знаю, как это будет работать на сервере и нормально ли это, нужно будет еще почитать, надо бы еще вызов в постконструкт запихать и посмотреть когда вызывается
    //так нельзя,сервак перегружается и подлагивает
    //перенесьи в тесты
    public void fillSystemProperties() {



        try(FileWriter writer = new FileWriter("target\\classes\\systemProperties.properties", false))
        {
            StringBuilder text=new StringBuilder();
            text.append("properties.maxPrice=").append(productRepo.findMaxPrice()).append("\n");
            text.append("properties.minPrice=").append(productRepo.findMinPrice()).append("\n");
            text.append("properties.maxFrame=").append(productRepo.findMaxFrame()).append("\n");
            text.append("properties.minFrame=").append(productRepo.findMinFrame()).append("\n");
            writer.append(text.toString());
            writer.flush();
        }
        catch(IOException ex){
            log.error("Не смог записать SystemProperties в файл конфигурации");
        }
    }
}
