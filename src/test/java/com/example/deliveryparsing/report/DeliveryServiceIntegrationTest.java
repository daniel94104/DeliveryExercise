package com.example.deliveryparsing.report;

import static com.example.deliveryparsing.report.TestingConstants.BUSINESS;
import static com.example.deliveryparsing.report.TestingConstants.POLITICS;
import static com.example.deliveryparsing.report.TestingConstants.SPORTS;
import static com.example.deliveryparsing.report.TestingConstants.TRAVEL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.deliveryparsing.report.dtos.DateRangeReportRequest;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class DeliveryServiceIntegrationTest {
  private final long SPORTS_TOTAL_IMPRESSIONS = 318677;
  private final long BUSINESS_TOTAL_IMPRESSIONS = 277954;
  private final long TRAVEL_TOTAL_IMPRESSIONS = 312176;
  private final long POLITICS_TOTAL_IMPRESSIONS = 217978;
  @Autowired private DeliveryService deliveryService;

  @Test
  public void whenFindDeliveriesWithinValidDateRange_Success() throws ParseException {
    // setup
    final var startDateString = "11/22/2020";
    final var endDateString = "12/5/2020";
    var dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    var startDate = dateFormat.parse(startDateString);
    var endDate = dateFormat.parse(endDateString);

    // when
    var dateRangeReportRequest = new DateRangeReportRequest();
    dateRangeReportRequest.setStartDate(startDate);
    dateRangeReportRequest.setEndDate(endDate);
    var aggregateDeliveryItems =
        deliveryService.findDeliveriesWithinDateRange(dateRangeReportRequest);

    // assert
    assertEquals(4, aggregateDeliveryItems.size());
    for (var aggregateDeliveryItem : aggregateDeliveryItems) {
      switch (aggregateDeliveryItem.getPlacement().getName()) {
        case SPORTS -> assertEquals(SPORTS_TOTAL_IMPRESSIONS,
            aggregateDeliveryItem.getDeliveryTotalImpressions());
        case BUSINESS -> assertEquals(BUSINESS_TOTAL_IMPRESSIONS,
            aggregateDeliveryItem.getDeliveryTotalImpressions());
        case TRAVEL -> assertEquals(TRAVEL_TOTAL_IMPRESSIONS,
            aggregateDeliveryItem.getDeliveryTotalImpressions());
        case POLITICS -> assertEquals(POLITICS_TOTAL_IMPRESSIONS,
            aggregateDeliveryItem.getDeliveryTotalImpressions());
      }
    }
  }
}
