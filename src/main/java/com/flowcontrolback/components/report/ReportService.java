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

    public ApiResponse<Report> listByMonthAndYear(Month month, Integer year) {
        Optional<Report> report = repository.findByMonthAndYear(month, year);
        if(report.isEmpty()) {
            return new ApiResponse<Report>().of(HttpStatus.NOT_FOUND, "Nenhum relatório disponível.");
        }
        return new ApiResponse<Report>().of(HttpStatus.OK, "Relatório encontrado com sucesso.", report.get());
    }

    public Report create(Report report) {
        return repository.save(report);
    }

    public Report edit(Report report) {
        return repository.save(report);
    }

}
