package com.digis01.MMarinCajeroServices.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CrosFileConfig {
    @Bean
    public CorsFilter CorsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsconfig = new CorsConfiguration();
        corsconfig.addAllowedOrigin("*");
         corsconfig.addAllowedMethod("GET");      
        corsconfig.addAllowedMethod("POST");
        corsconfig.addAllowedMethod("PUT");
        corsconfig.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", corsconfig);
        return new CorsFilter(source);
    }
}
