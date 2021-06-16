package com.example.deliveryparsing.report.dtos;

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
  private long totalImpressions;
  private int costPerMile;
  private double totalCost;

  @Override
  public String toString() {
    var dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    var numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
    numberFormat.setGroupingUsed(true);
    numberFormat.setMaximumFractionDigits(0);
    var decimalFormat = new DecimalFormat("#,###");
    return placementName
        + " ("
        + dateFormat.format(startDate)
        + "-"
        + dateFormat.format(endDate)
        + "): "
        + decimalFormat.format(totalImpressions)
        + " impressions @ "
        + numberFormat.format(costPerMile)
        + " CPM = "
        + numberFormat.format(totalCost);
  }
}
