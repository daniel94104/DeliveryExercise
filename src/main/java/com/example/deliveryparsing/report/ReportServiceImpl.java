package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.dtos.DateRangeReport;
import com.example.deliveryparsing.report.dtos.DateRangeReportRequest;
import com.example.deliveryparsing.report.dtos.DeliverySummaryCalculationRequest;
import com.example.deliveryparsing.report.dtos.ReportItem;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Component
public class ReportServiceImpl implements ReportService {
  private final @NonNull PlacementRepository placementRepository;
  private final @NonNull DeliveryService deliveryService;
  private final @NonNull ModelMapper modelMapper;
  private Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

  @Override
  public List<ReportItem> collectReportItems() {
    // if getting HUGE number of placements, need to do pagination.
    var totalPlacements = placementRepository.findAll();
    var reportItems = new ArrayList<ReportItem>();

    for (var placement : totalPlacements) {
      var deliverySummaryCreateRequest = new DeliverySummaryCalculationRequest();
      deliverySummaryCreateRequest.setPlacementName(placement.getName());
      deliverySummaryCreateRequest.setPlacementId(placement.getId());
      var deliverySummaryForPlacementResult =
          deliveryService.calculateDeliverySummary(deliverySummaryCreateRequest);
      if (deliverySummaryForPlacementResult.isEmpty()) {
        logger.info(
            "Delivery for placement: "
                + placement.getName()
                + "is empty. Continue to process next one");
        continue;
      }
      var deliverySummary = deliverySummaryForPlacementResult.get();
      var reportItem = new ReportItem();
      modelMapper.map(deliverySummary, reportItem);
      reportItem.setCostPerMile(placement.getCostPerMile());
      var totalCost =
          calculateDeliveryTotalCost(
              deliverySummary.getTotalImpressions(),
              placement.getCostPerMile(),
              placement.getDefaultCostPerUnit());
      totalCost = Math.round(totalCost);
      reportItem.setTotalCost(totalCost);
      reportItems.add(reportItem);
    }

    return reportItems;
  }

  @Override
  public DateRangeReport collectDateRangeReport(DateRangeReportRequest dateRangeReportRequest) {
    var aggregatedDeliveryItems =
        deliveryService.findDeliveriesWithinDateRange(dateRangeReportRequest);
    var dateRangeReport = new DateRangeReport();
    if (aggregatedDeliveryItems.isEmpty()) {
      return dateRangeReport;
    }
    var totalPlacementCost = Double.valueOf(0);
    var totalImpressions = 0L;
    for (var aggregatedDeliveryItem : aggregatedDeliveryItems) {
      var totalCostPerPlacement =
          calculateDeliveryTotalCost(
              aggregatedDeliveryItem.getDeliveryTotalImpressions(),
              aggregatedDeliveryItem.getPlacement().getCostPerMile(),
              aggregatedDeliveryItem.getPlacement().getDefaultCostPerUnit());
      totalPlacementCost = totalPlacementCost + totalCostPerPlacement;
      totalImpressions = totalImpressions + aggregatedDeliveryItem.getDeliveryTotalImpressions();
    }
    dateRangeReport.setStartDate(dateRangeReportRequest.getStartDate());
    dateRangeReport.setEndDate(dateRangeReportRequest.getEndDate());
    dateRangeReport.setTotalImpressions(totalImpressions);
    var roundedTotalPlacementCost = Math.round(totalPlacementCost);
    dateRangeReport.setTotalCost(roundedTotalPlacementCost);
    return dateRangeReport;
  }

  private double calculateDeliveryTotalCost(
      long totalImpressions, int costPerMile, int defaultCostPerUnit) {
    return totalImpressions * (double)costPerMile / defaultCostPerUnit;
  }
}
