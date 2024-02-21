package com.flowcontrolback.components.report;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByMonthAndYear(Month month, Integer year);
}
