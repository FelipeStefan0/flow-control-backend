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
@Table(name = "actions", schema = "public")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "report_id")
    @JsonIgnore
    private Report report;
}
