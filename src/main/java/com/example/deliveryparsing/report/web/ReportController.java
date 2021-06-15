package com.example.deliveryparsing.report.web;

import com.example.deliveryparsing.report.ReportService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {
  private final String REPORT_ENDPOINT = "/report";
  private final @NonNull ReportService reportService;

  @GetMapping(REPORT_ENDPOINT)
  public ResponseEntity<List<String>> report() {
    var reportItems = reportService.collectReportItems();
    var result = new ArrayList<String>();
    if (reportItems.isEmpty()) {
      var message = "No report available at this moment.";
      result.add(message);
      return new ResponseEntity<>(result, HttpStatus.OK);
    }

    for (var reportItem : reportItems) {
      result.add(reportItem.toString());
    }

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
