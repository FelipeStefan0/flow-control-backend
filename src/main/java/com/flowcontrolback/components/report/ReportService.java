package com.flowcontrolback.components.report;

import com.flowcontrolback.models.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository repository;

    public List<Report> list() {
        return repository.findAll();
    }

    public Optional<Report> findByMonthAndYear(Month month, Integer year) {
        Optional<Report> report = repository.findByMonthAndYear(month, year);
        return report;
    }

    public Report create(Report report) {
        report.setTotal_value(report.getIn_total_value() + report.getOut_total_value());
        return repository.save(report);
    }

    public Report edit(Report report) {
        report.setTotal_value(report.getIn_total_value() + report.getOut_total_value());
        return repository.save(report);
    }

}
