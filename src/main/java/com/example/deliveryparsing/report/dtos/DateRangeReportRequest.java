package com.example.deliveryparsing.report.dtos;

import java.util.Date;
import lombok.Data;

@Data
public class DateRangeReportRequest {
  private Date startDate;
  private Date endDate;
}
