package com.example.deliveryparsing.report;

import static com.example.deliveryparsing.report.TestingConstants.BUSINESS;
import static com.example.deliveryparsing.report.TestingConstants.POLITICS;
import static com.example.deliveryparsing.report.TestingConstants.SPORTS;
import static com.example.deliveryparsing.report.TestingConstants.TRAVEL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.deliveryparsing.report.dtos.DateRangeReportRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ReportServiceIntegrationTest {
  @Autowired private ReportService reportService;

  @Test
  public void whenCollectDefaultReports_Success() {
    // setup
    final long EXPECTED_SPORT_PLACEMENT_TOTAL_COST = 5418;
    final long EXPECTED_BUSINESS_PLACEMENT_TOTAL_COST = 12864;
    final long EXPECTED_TRAVEL_PLACEMENT_TOTAL_COST = 3108;
    final long EXPECTED_POLITICS_PLACEMENT_TOTAL_COST = 9179;
    final long EXPECTED_SPORT_PLACEMENT_TOTAL_IMPRESSIONS = 1083576;
    final long EXPECTED_BUSINESS_PLACEMENT_TOTAL_IMPRESSIONS = 1607958;
    final long EXPECTED_TRAVEL_PLACEMENT_TOTAL_IMPRESSIONS = 1035966;
    final long EXPECTED_POLITICS_PLACEMENT_TOTAL_IMPRESSIONS = 1529821;

    // when
    var reportItems = reportService.collectReportItems();

    // assert
    for (var reportItem : reportItems) {
      switch (reportItem.getPlacementName()) {
        case SPORTS -> {
          assertEquals(EXPECTED_SPORT_PLACEMENT_TOTAL_COST, reportItem.getTotalCost());
          assertEquals(
              EXPECTED_SPORT_PLACEMENT_TOTAL_IMPRESSIONS, reportItem.getTotalImpressions());
        }
        case BUSINESS -> {
          assertEquals(EXPECTED_BUSINESS_PLACEMENT_TOTAL_COST, reportItem.getTotalCost());
          assertEquals(
              EXPECTED_BUSINESS_PLACEMENT_TOTAL_IMPRESSIONS, reportItem.getTotalImpressions());
        }
        case TRAVEL -> {
          assertEquals(EXPECTED_TRAVEL_PLACEMENT_TOTAL_COST, reportItem.getTotalCost());
          assertEquals(
              EXPECTED_TRAVEL_PLACEMENT_TOTAL_IMPRESSIONS, reportItem.getTotalImpressions());
        }
        case POLITICS -> {
          assertEquals(EXPECTED_POLITICS_PLACEMENT_TOTAL_COST, reportItem.getTotalCost());
          assertEquals(
              EXPECTED_POLITICS_PLACEMENT_TOTAL_IMPRESSIONS, reportItem.getTotalImpressions());
        }
      }
    }
  }

  @Test
  public void givenSampleDateRange_whenCalculateDateRangeReport_thenResultAsExpected()
      throws ParseException {
    // setup
    final long TOTAL_IMPRESSIONS = 1126785;
    final long TOTAL_COST = 6061;
    final var startDateString = "11/22/2020";
    final var endDateString = "12/5/2020";
    var dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    var startDate = dateFormat.parse(startDateString);
    var endDate = dateFormat.parse(endDateString);

    // when
    var dateRangeReportRequest = new DateRangeReportRequest();
    dateRangeReportRequest.setStartDate(startDate);
    dateRangeReportRequest.setEndDate(endDate);
    var report = reportService.collectDateRangeReport(dateRangeReportRequest).get();

    // assert
    assertEquals(TOTAL_COST, report.getTotalCost());
    assertEquals(TOTAL_IMPRESSIONS, report.getTotalImpressions());
  }

  @Test
  public void givenOutofRangeDate_whenCalculateDateRangeReport_thenResultEmpty()
      throws ParseException {
    // setup
    final var startDateString = "1/1/2021";
    final var endDateString = "12/31/2021";
    var dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    var startDate = dateFormat.parse(startDateString);
    var endDate = dateFormat.parse(endDateString);

    // when
    var dateRangeReportRequest = new DateRangeReportRequest();
    dateRangeReportRequest.setStartDate(startDate);
    dateRangeReportRequest.setEndDate(endDate);
    var report = reportService.collectDateRangeReport(dateRangeReportRequest);

    // assert
    assertTrue(report.isEmpty());
  }
}
