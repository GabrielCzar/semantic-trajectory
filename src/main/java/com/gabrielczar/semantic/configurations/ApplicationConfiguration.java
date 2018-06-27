package com.gabrielczar.semantic.configurations;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    JtsModule jtsModule() {
        return new JtsModule();
    }

}
