package com.example.deliveryparsing.webpagecontrollers;

import com.example.deliveryparsing.report.ReportService;
import com.example.deliveryparsing.report.dtos.DateRangeReportRequest;
import com.example.deliveryparsing.report.dtos.ReportItem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class WebPageControllers {
  private final String INDEX_VIEW = "index";
  private final String DATE_RANGE_REPORT_REQUEST = "dateRangeReportRequest";
  private final String DATE_RANGE_REPORT_ATTRIBUTE = "dateRangeReport";
  private final String DATE_RANGE_REPORT_VIEW = "dateRangeReport";
  private final String DEFAULT_REPORT_ATTRIBUTE = "defaultReport";
  private final String START_DATE_ATTRIBUTE = "startDate";
  private final String END_DATE_ATTRIBUTE = "endDate";
  private final @NonNull ReportService reportService;

  @GetMapping("/index")
  public String index(Model model) {
    var dateRangeReportRequest = new DateRangeReportRequest();
    model.addAttribute(DATE_RANGE_REPORT_ATTRIBUTE, "");
    model.addAttribute(DEFAULT_REPORT_ATTRIBUTE, new ArrayList<ReportItem>());
    model.addAttribute(DATE_RANGE_REPORT_REQUEST, dateRangeReportRequest);
    return INDEX_VIEW;
  }

  @GetMapping("/default_report")
  public String getDefaultReport(Model model) {
    var reportItems = reportService.collectReportItems();
    var dateRangeReportRequest = new DateRangeReportRequest();
    model.addAttribute(DEFAULT_REPORT_ATTRIBUTE, reportItems);
    model.addAttribute(DATE_RANGE_REPORT_ATTRIBUTE, "");
    model.addAttribute(DATE_RANGE_REPORT_REQUEST, dateRangeReportRequest);
    return INDEX_VIEW;
  }

  @GetMapping("/time_range_report")
  public String getTimeRangeReport(
      @Valid @ModelAttribute(DATE_RANGE_REPORT_REQUEST)
          DateRangeReportRequest dateRangeReportRequest,
      Model model) {
    var simepleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    var startDate = simepleDateFormat.format(dateRangeReportRequest.getStartDate());
    var endDate = simepleDateFormat.format(dateRangeReportRequest.getEndDate());
    var dateRangeReport = reportService.collectDateRangeReport(dateRangeReportRequest);
    model.addAttribute(DATE_RANGE_REPORT_ATTRIBUTE, dateRangeReport.toString());
    model.addAttribute(START_DATE_ATTRIBUTE, startDate);
    model.addAttribute(END_DATE_ATTRIBUTE, endDate);
    return DATE_RANGE_REPORT_VIEW;
  }
}
