package com.example.deliveryparsing.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** ModelMapper Singleton Bean */
@Configuration
public class ModelMapperConfiguration {
  @Bean
  public ModelMapper modelMapper() {
    var modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    modelMapper
        .getConfiguration()
        .setFieldAccessLevel(AccessLevel.PRIVATE)
        .setFieldMatchingEnabled(true);
    return modelMapper;
  }
}
