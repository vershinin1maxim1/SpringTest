package com.example.sweater.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@PropertySource("classpath:systemProperties.properties")
@ConfigurationProperties(prefix = "properties")
public class SystemPropertiesConfig {

    private int maxPrice;
    private int minPrice;
}
