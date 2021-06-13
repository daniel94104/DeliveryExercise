package com.example.deliveryparsing.report.dtos;

import java.util.Date;
import lombok.Data;

@Data
public class PlacementCreateResponse {
  private long id;
  private String name;
  private Date startTime;
  private Date endTime;
  private int costPerMile;
}
