package com.PolKevich.ITBcamp0824.configuration;

import com.PolKevich.ITBcamp0824.mappers.AppMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

    @Bean
public AppMapper appMapper(){
        return Mappers.getMapper(AppMapper.class);
    }
}
