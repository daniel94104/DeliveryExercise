package com.example.deliveryparsing.report.dtos;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import lombok.Data;

@Data
public class ReportItem {
  private String placementName;
  private Date startDate;
  private Date endDate;
  private BigInteger totalImpressions;
  private int costPerMile;
  private int totalCost;

  @Override
  public String toString() {
    var dateFormat = new SimpleDateFormat("MM/dd/yyyyy");
    var numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
    numberFormat.setGroupingUsed(true);
    numberFormat.setMaximumFractionDigits(0);
    var decimalFormat = new DecimalFormat("#,###");
    return placementName
        + " ("
        + dateFormat.format(startDate)
        + "-"
        + dateFormat.format(endDate)
        + ") "
        + decimalFormat.format(totalImpressions.intValue())
        + " impressions @ "
        + numberFormat.format(costPerMile)
        + " CPM = "
        + numberFormat.format(totalCost);
  }
}
