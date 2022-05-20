package com.example.deliveryparsing.webpagecontrollers;

import com.example.deliveryparsing.report.dtos.DateRangeReportRequest;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
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

  @GetMapping("/index")
  public String index(Model model) {
    var dateRangeReportRequest = new DateRangeReportRequest();
    model.addAttribute(DATE_RANGE_REPORT_ATTRIBUTE, "");
    model.addAttribute(DATE_RANGE_REPORT_REQUEST, dateRangeReportRequest);
    return INDEX_VIEW;
  }

  @GetMapping("/default_report")
  public String getDefaultReport(Model model) {
    return INDEX_VIEW;
  }

  @GetMapping("/time_range_report")
  public String getTimeRangeReport(
      @Valid @ModelAttribute(DATE_RANGE_REPORT_REQUEST)
          DateRangeReportRequest dateRangeReportRequest,
      Model model) {

    return DATE_RANGE_REPORT_VIEW;
  }
}
