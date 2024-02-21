package com.flowcontrolback.report;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.time.Year;

@Entity
@Data
@NoArgsConstructor
@Table(name = "report", schema = "report")
public class Report {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "month", nullable = false)
    private Month month;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "total_value", nullable = false)
    private Double totalValue;

    @Column(name = "in_total_value", nullable = false)
    private Double inTotalValue;

    @Column(name = "out_total_value", nullable = false)
    private Double outTotalValue;
}
