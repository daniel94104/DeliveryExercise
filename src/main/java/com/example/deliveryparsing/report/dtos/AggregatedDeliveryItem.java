package com.example.deliveryparsing.report.dtos;

import com.example.deliveryparsing.report.models.Placement;
import java.math.BigInteger;
import lombok.Data;

@Data
public class AggregatedDeliveryItem {
  private Placement placement;
  private BigInteger deliveryTotalImpressions;

  public AggregatedDeliveryItem(Placement placement, BigInteger deliveryTotalImpressions) {
    this.placement = placement;
    this.deliveryTotalImpressions = deliveryTotalImpressions;
  }
}
