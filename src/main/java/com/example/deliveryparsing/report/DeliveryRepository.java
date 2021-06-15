package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.models.Delivery;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
  List<Delivery> findDeliveriesByPlacementIdOrderByDateAsc(long placementId);
}
