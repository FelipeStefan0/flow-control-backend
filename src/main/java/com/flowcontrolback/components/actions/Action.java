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
@Table(name = "actions", schema = "registers")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double value;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime date;

    private String description;

    @Enumerated(EnumType.STRING)
    private TypesActions type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "report_id")
    @JsonIgnore
    private Report report;
}
