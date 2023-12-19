package com.flowcontrolback.entities;

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

    @Column(name = "value_action", nullable = false)
    private double value_action;

    @Column(name = "date_action", nullable = false, columnDefinition = "timestamp")
    private LocalDateTime date_action;

    @Column(name = "notes")
    private String notes;

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "types_action", nullable = false)
    //private TypesActions types_actions;
}
