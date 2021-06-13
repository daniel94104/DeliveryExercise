package com.example.deliveryparsing.report.dtos;

import java.math.BigInteger;
import java.util.Date;
import lombok.Data;

@Data
public class DeliveryCreateResponse {
  private long placementId;
  private Date date;
  private BigInteger impression;
}
