package com.gabrielczar.semantic.configurations;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.gabrielczar.semantic.services.StorageService;
import main.java.matching.controller.MatchingService;
import main.java.matching.services.PreProcess;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public JtsModule jtsModule() { return new JtsModule(); }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("50MB");
        factory.setMaxRequestSize("50MB");
        return factory.createMultipartConfig();
    }

    @Bean
    public PreProcess preProcess() { return new PreProcess(); }

    @Bean
    public MatchingService matchingService() { return new MatchingService(); }

    @Bean
    public StorageService storageService() {
        return new StorageService("storage");
    }
}
