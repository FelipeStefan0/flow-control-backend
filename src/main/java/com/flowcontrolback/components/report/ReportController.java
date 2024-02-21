package com.flowcontrolback.components.report;

import com.flowcontrolback.models.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/reports")
@AllArgsConstructor
public class ReportController {

    private final ReportService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Report>>> getAll() {
        ApiResponse<List<Report>> response = new ApiResponse<>();
        List<Report> reports = service.list();
        response.of(HttpStatus.OK, "Relatórios encontrados com sucesso.", reports);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/report")
    public ResponseEntity<ApiResponse<Report>> getReport(@RequestParam Month month, @RequestParam Integer year) {
        ApiResponse<Report> response = service.listByMonthAndYear(month, year);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Report>> create(@RequestBody Report report) {
        ApiResponse<Report> response = new ApiResponse<>();
        Report createdReport = service.create(report);
        response.of(HttpStatus.OK, "Relatório criado com sucesso.", report);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
