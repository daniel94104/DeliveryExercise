package com.example.deliveryparsing.report;

import com.example.deliveryparsing.report.dtos.AggregatedDeliveryItem;
import com.example.deliveryparsing.report.models.Delivery;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
  List<Delivery> findDeliveriesByPlacementIdOrderByDateAsc(long placementId);

  @Query(
      value =
          "select new com.example.deliveryparsing.report.dtos.AggregatedDeliveryItem(d.placement, sum(d.impressions)) from Delivery d where d.date >= :startDate and d.date <= :endDate group by d.placement")
  List<AggregatedDeliveryItem> findDeliveriesWithinDateRangeAndGroupByPlacementIdAndSumImpressions(
      @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
