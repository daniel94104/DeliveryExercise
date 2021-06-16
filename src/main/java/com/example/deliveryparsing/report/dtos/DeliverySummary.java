package com.example.deliveryparsing.report.dtos;

import java.util.Date;
import lombok.Data;

@Data
public class DeliverySummary {
  private String placementName;
  private Date startDate;
  private Date endDate;
  private long totalImpressions;
}
