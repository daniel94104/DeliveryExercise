package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.dtos.DateRangeReport;
import com.example.deliveryparsing.report.dtos.DateRangeReportRequest;
import com.example.deliveryparsing.report.dtos.ReportItem;
import java.util.List;
import java.util.Optional;

public interface ReportService {
  List<ReportItem> collectReportItems();

  Optional<DateRangeReport> collectDateRangeReport(DateRangeReportRequest dateRangeReportRequest);
}
