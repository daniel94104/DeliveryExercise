package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.dtos.PlacementCreateRequest;
import com.example.deliveryparsing.report.dtos.PlacementCreateResponse;
import com.example.deliveryparsing.report.models.Placement;
import java.util.Optional;

public interface PlacementService {
  Optional<PlacementCreateResponse> create(PlacementCreateRequest placementCreateRequest);

  Optional<Placement> findById(long id);
}
