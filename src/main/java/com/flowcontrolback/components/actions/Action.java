package com.flowcontrolback.components.actions;

import com.flowcontrolback.models.TypesActions;
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

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "hours", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime hours;

    @Column(name = "notes")
    private String notes;

    @Column(name = "types")
    private TypesActions types;
}
