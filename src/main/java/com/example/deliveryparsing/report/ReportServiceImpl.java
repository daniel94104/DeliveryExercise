package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.dtos.DeliverySummary;
import com.example.deliveryparsing.report.dtos.DeliverySummaryCalculationRequest;
import com.example.deliveryparsing.report.dtos.ReportItem;
import com.example.deliveryparsing.report.models.Placement;
import java.math.BigInteger;
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
    var totalPlacements =
        placementRepository
            .findAll(); // if getting HUGE number of placements, need to do pagination.
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
      var totalCost = calculateDeliveryTotalCost(placement, deliverySummary);
      reportItem.setTotalCost(totalCost);
      reportItems.add(reportItem);
    }

    return reportItems;
  }

  private int calculateDeliveryTotalCost(Placement placement, DeliverySummary deliverySummary) {
    var totalCost =
        deliverySummary
            .getTotalImpressions()
            .divide(new BigInteger(String.valueOf(placement.getDefaultCostPerUnit())))
            .multiply(new BigInteger(String.valueOf(placement.getCostPerMile())));
    return totalCost.intValue();
  }
}
