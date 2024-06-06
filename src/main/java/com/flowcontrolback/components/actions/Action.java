package com.flowcontrolback.components.actions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flowcontrolback.components.report.Report;
import com.flowcontrolback.models.enums.TypesActions;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "action", schema = "actions")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private TypesActions type;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Report.class, optional = false)
    @JoinColumn(name = "report_id", nullable = false)
    @JsonIgnore
    private Report report;
}
