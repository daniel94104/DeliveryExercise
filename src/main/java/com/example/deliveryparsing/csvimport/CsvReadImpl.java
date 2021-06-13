package com.example.deliveryparsing.csvimport;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CsvReadImpl implements CsvReader {
  private final Logger log = LoggerFactory.getLogger(CsvReadImpl.class);

  @Override
  public <T> List<T> readCsvFromClassResource(Class<T> targetDto, String resourceName) {
    try {
      var resource = new ClassPathResource(resourceName);
      var resourceStreamReader = new InputStreamReader(resource.getInputStream(), "UTF-8");
      var targets = new CsvToBeanBuilder(resourceStreamReader).withType(targetDto).build().parse();
      resourceStreamReader.close();
      return targets;
    } catch (Exception exception) {
      log.error("Failed to load csv resource: " + resourceName + ". Detail: " + exception);
      return Collections.emptyList();
    }
  }
}
