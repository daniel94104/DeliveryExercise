package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.dtos.AggregatedDeliveryItem;
import com.example.deliveryparsing.report.dtos.DateRangeReportRequest;
import com.example.deliveryparsing.report.dtos.DeliveryCreateRequest;
import com.example.deliveryparsing.report.dtos.DeliveryCreateResponse;
import com.example.deliveryparsing.report.dtos.DeliverySummary;
import com.example.deliveryparsing.report.dtos.DeliverySummaryCalculationRequest;
import com.example.deliveryparsing.report.models.Delivery;
import java.text.SimpleDateFormat;
import java.util.List;
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
public class DeliveryServiceImpl implements DeliveryService {

  private final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);
  private final @NonNull PlacementService placementService;
  private final @NonNull DeliveryRepository deliveryRepository;
  private final @NonNull ModelMapper modelMapper;

  @Override
  public Optional<DeliveryCreateResponse> create(DeliveryCreateRequest deliveryCreateRequestDto) {
    var deliveryCreateResponse = new DeliveryCreateResponse();
    var hasPlacement = placementService.findById(deliveryCreateRequestDto.getPlacementId());
    if (hasPlacement.isEmpty()) {
      logger.error(
          "Invalid delivery create request, no related placement found with id: "
              + deliveryCreateRequestDto.getPlacementId());
      return Optional.empty();
    }
    var delivery = new Delivery();
    delivery.setPlacement(hasPlacement.get());
    var dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    try {
      delivery.setDate(dateFormat.parse(deliveryCreateRequestDto.getDate()));
    } catch (Exception exception) {
      logger.error(
          "Unable to create delivery. Invalid date format, details: " + exception.getMessage());
      return Optional.empty();
    }
    delivery.setImpressions(deliveryCreateRequestDto.getImpressions());

    delivery = deliveryRepository.save(delivery);
    var deliverCreateResponse = new DeliveryCreateResponse();
    modelMapper.map(delivery, deliverCreateResponse);
    return Optional.of(deliverCreateResponse);
  }

  @Override
  public Optional<DeliverySummary> calculateDeliverySummary(
      DeliverySummaryCalculationRequest deliverySummaryCalculationRequest) {
    // Todo
    return Optional.empty();
  }

  @Override
  public List<AggregatedDeliveryItem> findDeliveriesWithinDateRange(
      DateRangeReportRequest dateRangeReportRequest) {
    // Todo
    return null;
  }
}
