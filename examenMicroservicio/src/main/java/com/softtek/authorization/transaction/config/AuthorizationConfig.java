package com.softtek.authorization.transaction.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableSpringDataWebSupport 
public class AuthorizationConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    } 
}
