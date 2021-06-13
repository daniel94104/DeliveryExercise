package com.example.deliveryparsing.csvimport;

import java.util.List;

public interface CsvReader {
  <T> List<T> readCsvFromClassResource(Class<T> targetDto, String resourceName);
}
