package com.example.deliveryparsing.report.models;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Delivery {
  @Id @GeneratedValue private Long id;

  @OneToOne private Placement placement;

  @Column(nullable = false)
  private Date date;

  private BigInteger impressions;
}
