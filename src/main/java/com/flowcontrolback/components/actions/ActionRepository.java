package com.flowcontrolback.components.actions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

public interface ActionRepository extends JpaRepository<Action, Long> {
    Optional<Action> existsActionByHoursMonthAndHoursYear(Month month, int year);
}
