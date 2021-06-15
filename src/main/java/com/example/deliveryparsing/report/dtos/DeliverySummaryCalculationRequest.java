package com.example.deliveryparsing.report.dtos;

import lombok.Data;

@Data
public class DeliverySummaryCalculationRequest {
  private long placementId;
  private String placementName;
}
