package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.dtos.DeliveryCreateRequest;
import com.example.deliveryparsing.report.dtos.DeliveryCreateResponse;
import com.example.deliveryparsing.report.dtos.DeliverySummary;
import com.example.deliveryparsing.report.dtos.DeliverySummaryCalculationRequest;
import java.util.Optional;

public interface DeliveryService {
  Optional<DeliveryCreateResponse> create(DeliveryCreateRequest deliveryCreateRequestDto);

  Optional<DeliverySummary> calculateDeliverySummary(
      DeliverySummaryCalculationRequest deliverySummaryCalculationRequest);
}
