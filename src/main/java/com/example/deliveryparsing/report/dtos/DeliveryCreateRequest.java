package com.example.deliveryparsing.report.dtos;

import java.math.BigInteger;
import lombok.Data;

@Data
public class DeliveryCreateRequest {
  private long placementId;
  private String date;
  private BigInteger impression;
}
