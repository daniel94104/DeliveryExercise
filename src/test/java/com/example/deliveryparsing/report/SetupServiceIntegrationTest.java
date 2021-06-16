package com.example.deliveryparsing.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SetupServiceIntegrationTest {
  private final int EXPECTED_NUMBER_OF_PLACEMENTS = 4;
  private final int EXPECTED_NUMBER_OF_DELIVERIES = 122;
  @Autowired private PlacementRepository placementRepository;
  @Autowired private DeliveryRepository deliveryRepository;

  @Test
  public void whenSetupPlacementsAndDelivery_Success() {
    // unnecessary to call SetupService, since it is called already on PostConstruct.
    var placements = placementRepository.findAll();
    var deliveries = deliveryRepository.findAll();
    assertEquals(EXPECTED_NUMBER_OF_PLACEMENTS, placements.size());
    assertEquals(EXPECTED_NUMBER_OF_DELIVERIES, deliveries.size());
  }
}
