package com.flowcontrolback.components.report;

import com.flowcontrolback.models.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Optional;

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
        Optional<Report> data = service.findByMonthAndYear(month, year);
        ApiResponse<Report> response = new ApiResponse<>();
        if(data.isEmpty()) {
            response.of(HttpStatus.NOT_FOUND, "Nenhum relatório disponível.");
        } else {
            response.of(HttpStatus.OK, "Relatório encontrado com sucesso.", data.get());
        }
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
