package com.example.deliveryparsing.report.dtos;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class PlacementCreateRequest {
  @CsvBindByName(column = "id")
  private Long id;

  @CsvBindByName(column = "name")
  private String name;

  @CsvBindByName(column = "start")
  private String startTime;

  @CsvBindByName(column = "end")
  private String endTime;

  @CsvBindByName(column = "cpm")
  private int costPerMile;
}
