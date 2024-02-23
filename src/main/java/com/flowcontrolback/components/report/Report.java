package com.flowcontrolback.components.report;

import com.flowcontrolback.components.actions.Action;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report", schema = "report")
@Builder
public class Report {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "month", nullable = false)
    private Month month;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "total_value", nullable = false)
    private Double total_value;

    @Column(name = "in_total_value", nullable = false)
    private Double in_total_value;

    @Column(name = "out_total_value", nullable = false)
    private Double out_total_value;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "report", cascade = CascadeType.ALL)
    private List<Action> actions;
}
