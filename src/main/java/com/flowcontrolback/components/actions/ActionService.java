package com.flowcontrolback.components.actions;

import com.flowcontrolback.components.report.Report;
import com.flowcontrolback.components.report.ReportRepository;
import com.flowcontrolback.components.report.ReportService;
import com.flowcontrolback.models.TypesActions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository repository;
    private final ReportService reportService;

    public List<Action> list() {
        List<Action> response = null;
        response = repository.findAll();
        return response;
    }
    
    public Action create(Action action) {
        action.setHours(new Timestamp(System.currentTimeMillis()).toLocalDateTime());

        Integer year = action.getHours().getYear();
        Month month = action.getHours().getMonth();
        if(reportService.findByMonthAndYear(month, year).isEmpty()) {
            Report report = Report.builder()
                    .month(month)
                    .year(year)
                    .total_value(0.0)
                    .build();
            if (action.getTypes() == TypesActions.IN) {
                report.setIn_total_value(action.getAmount());
                report.setOut_total_value(0.0);
            } else {
                report.setOut_total_value(action.getAmount());
                report.setIn_total_value(0.0);
            }
            reportService.create(report);
        } else {
            Report report = reportService.findByMonthAndYear(month, year).get();
            if (action.getTypes() == TypesActions.IN) {
                report.setIn_total_value(report.getIn_total_value() + action.getAmount());
            } else {
                report.setOut_total_value(report.getOut_total_value() - action.getAmount());
            }
            reportService.edit(report);
        }

        Action response = null;
        response = repository.save(action);
        return response;
    }

    public void delete(Long id) throws Exception {
        repository.deleteById(id);
    }

    public Action edit(Action action) {
        LocalDateTime hours = repository.findById(action.getId()).get().getHours();
        action.setHours(hours);
        Action response = action;
        response = repository.save(action);
        return response;
    }
}
