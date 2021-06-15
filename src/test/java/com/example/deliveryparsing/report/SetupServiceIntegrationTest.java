package com.example.deliveryparsing.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SetupServiceIntegrationTest {
  @Autowired private SetupService setupService;
  @Autowired private PlacementRepository placementRepository;

  private final int EXPECTED_NUMBER_OF_PLACEMENTS = 4;
  private final int EXPECTED_NUMBER_OF_DELIVERIES = 122;

  @Test
  public void whenSetupPlacementsAndDelivery_Success() {
    setupService.setupPlacementAndDelivery();
    var placements = placementRepository.findAll();

    assertEquals(EXPECTED_NUMBER_OF_PLACEMENTS, placements.size());
  }
}
