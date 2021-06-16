package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.dtos.DateRangeReport;
import com.example.deliveryparsing.report.dtos.DateRangeReportRequest;
import com.example.deliveryparsing.report.dtos.ReportItem;
import java.util.List;

public interface ReportService {
  List<ReportItem> collectReportItems();

  DateRangeReport collectDateRangeReport(DateRangeReportRequest dateRangeReportRequest);
}
