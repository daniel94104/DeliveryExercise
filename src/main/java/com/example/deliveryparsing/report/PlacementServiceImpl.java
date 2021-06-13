package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.dtos.PlacementCreateRequest;
import com.example.deliveryparsing.report.dtos.PlacementCreateResponse;
import com.example.deliveryparsing.report.models.Placement;
import com.example.deliveryparsing.utils.DateUtils;
import java.util.Optional;
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
public class PlacementServiceImpl implements PlacementService {
  private final Logger logger = LoggerFactory.getLogger(SetupServiceImpl.class);

  private final @NonNull PlacementRepository placementRepository;
  private final @NonNull ModelMapper modelMapper;
  private final @NonNull DateUtils dateUtils;

  @Override
  public Optional<PlacementCreateResponse> create(
      PlacementCreateRequest placementCreateRequest) {
    var placement = new Placement();
    placement.setId(placementCreateRequest.getId());
    placement.setCostPerMile(placementCreateRequest.getCostPerMile());
    placement.setName(placementCreateRequest.getName());
    var startTimeConvertResult =
        dateUtils.convertTwoDigitYearDateToStandardDate(placementCreateRequest.getStartTime());
    var endTimeConvertResult =
        dateUtils.convertTwoDigitYearDateToStandardDate(placementCreateRequest.getEndTime());

    if (startTimeConvertResult.isEmpty() || endTimeConvertResult.isEmpty()) {
      logger.error(
          "Fail to create placement. Start time convert result: "
              + startTimeConvertResult.isPresent()
              + ". End time convert result: "
              + endTimeConvertResult.isPresent());
      return Optional.empty();
    }

    placement.setStartTime(startTimeConvertResult.get());
    placement.setEndTime(endTimeConvertResult.get());

    placement = placementRepository.save(placement);

    var placementCreateResponse = new PlacementCreateResponse();
    modelMapper.map(placement, placementCreateResponse);
    return Optional.of(placementCreateResponse);
  }
}
