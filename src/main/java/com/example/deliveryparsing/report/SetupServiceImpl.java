package com.example.deliveryparsing.report;

import com.example.deliveryparsing.csvimport.CsvReader;
import com.example.deliveryparsing.report.dtos.DeliveryCreateRequest;
import com.example.deliveryparsing.report.dtos.DeliveryCreateResponse;
import com.example.deliveryparsing.report.dtos.PlacementCreateRequest;
import com.example.deliveryparsing.report.dtos.PlacementCreateResponse;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Component
public class SetupServiceImpl implements SetupService {
  private final Logger logger = LoggerFactory.getLogger(SetupServiceImpl.class);
  private final String placementCsvFileName = "placements.csv";
  private final String deliveryCscFileName = "delivery.csv";

  private final @NonNull CsvReader csvReader;
  private final @NonNull PlacementService placementService;
  private final @NonNull DeliveryService deliveryService;

  @Override
  @PostConstruct
  public void setupPlacementAndDelivery() {
    readPlacements();
    readDeliveries();
  }

  private void readPlacements() {
    var placementCreateRequests =
        csvReader.readCsvFromClassResource(PlacementCreateRequest.class, placementCsvFileName);
    var savedPlacements = new ArrayList<PlacementCreateResponse>();
    for (var placementCreateRequest : placementCreateRequests) {
      var savedPlacement = placementService.create(placementCreateRequest);
      if (savedPlacement.isEmpty()) {
        logger.error("Setup failed to save placement: " + placementCreateRequest.getName());
        continue;
      }
      savedPlacements.add(savedPlacement.get());
    }
    logger.info(
        "setupPlacementAndDelivery: successfully saved " + savedPlacements.size() + " placements");
  }

  private void readDeliveries() {
    var deliveryCreateCreateRequests =
        csvReader.readCsvFromClassResource(DeliveryCreateRequest.class, deliveryCscFileName);
    var savedDeliveries = new ArrayList<DeliveryCreateResponse>();
    for (var deliveryCreateRequest : deliveryCreateCreateRequests) {
      var savedDelivery = deliveryService.create(deliveryCreateRequest);
      if (savedDelivery.isEmpty()) {
        logger.error("Setup failed to save delivery on date: " + deliveryCreateRequest.getDate());
        continue;
      }
      savedDeliveries.add(savedDelivery.get());
    }
    logger.info(
        "setupPlacementAndDelivery: successfully saved " + savedDeliveries.size() + " placements");
  }
}
