package com.example.deliveryparsing.report.dtos;

import com.opencsv.bean.CsvBindByName;
import java.math.BigInteger;
import lombok.Data;

@Data
public class DeliveryCreateRequest {
  @CsvBindByName(column = "placement_id")
  private long placementId;
  @CsvBindByName(column = "date")
  private String date;
  @CsvBindByName(column = "impressions")
  private BigInteger impressions;
}
