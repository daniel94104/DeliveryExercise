package com.example.deliveryparsing.report.dtos;

import java.math.BigInteger;
import java.util.Date;
import lombok.Data;

@Data
public class DeliverySummary {
  private String placementName;
  private Date startDate;
  private Date endDate;
  private BigInteger totalImpressions = new BigInteger("0");
}
