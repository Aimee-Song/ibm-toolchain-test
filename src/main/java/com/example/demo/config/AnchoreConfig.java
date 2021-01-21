package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("anchore")
@Data
public class AnchoreConfig {

    private String url;

    private String authorization;

}
