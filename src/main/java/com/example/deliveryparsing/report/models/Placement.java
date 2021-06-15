package com.example.deliveryparsing.report.models;

import com.opencsv.bean.CsvBindByName;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Data
@Table(
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "id"),
      @UniqueConstraint(columnNames = "name")
    })
public class Placement {
  @CsvBindByName(column = "id")
  @Id
  private Long id;

  @Column(nullable = false)
  @CsvBindByName(column = "name")
  private String name;

  @Column(nullable = false)
  @CsvBindByName(column = "start")
  private Date startTime;

  @Column(nullable = false)
  @CsvBindByName(column = "end")
  private Date endTime;

  @Column(nullable = false)
  @CsvBindByName(column = "cpm")
  private int costPerMile;

  private int defaultCostPerUnit = 1000;
}
