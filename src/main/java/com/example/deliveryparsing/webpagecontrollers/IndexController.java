package com.example.deliveryparsing.webpagecontrollers;

import com.example.deliveryparsing.report.ReportService;
import com.example.deliveryparsing.report.dtos.DateRangeReportRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class IndexController {
  private final String INDEX_VIEW = "index";
  private final String DATE_RANGE_REPORT_REQUEST = "dateRangeReportRequest";
  private final String DATE_RANGE_REPORT = "dateRangeReport";
  private final @NonNull ReportService reportService;

  @GetMapping("/index")
  public String index(Model model) {
    var dateRangeReportRequest = new DateRangeReportRequest();
    model.addAttribute(DATE_RANGE_REPORT, "");
    model.addAttribute(DATE_RANGE_REPORT_REQUEST, dateRangeReportRequest);
    return INDEX_VIEW;
  }

  @GetMapping("/time_range_report")
  public String getTimeRangeReport(
      @Valid @ModelAttribute(DATE_RANGE_REPORT_REQUEST)
          DateRangeReportRequest dateRangeReportRequest,
      BindingResult result,
      Model model) {

    var dateRangeReport = reportService.collectDateRangeReport(dateRangeReportRequest);
    model.addAttribute(DATE_RANGE_REPORT, dateRangeReport.toString());
    var redirectView = new RedirectView(INDEX_VIEW, true);
    redirectView.addStaticAttribute(DATE_RANGE_REPORT, dateRangeReport.toString());
    return INDEX_VIEW;
  }
}
