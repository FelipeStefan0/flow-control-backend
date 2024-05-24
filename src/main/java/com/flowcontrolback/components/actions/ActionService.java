package com.flowcontrolback.components.actions;

import com.flowcontrolback.components.report.Report;
import com.flowcontrolback.components.report.ReportRepository;
import com.flowcontrolback.components.report.ReportService;
import com.flowcontrolback.models.ApiResponse;
import com.flowcontrolback.models.Interval;
import com.flowcontrolback.models.TypesActions;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
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
        Integer year = action.getHours().getYear();
        Month month = action.getHours().getMonth();
        List<Action> actions = new ArrayList<>();
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
            report.setActions(actions);
            reportService.create(report);
        } else {
            Report report = reportService.findByMonthAndYear(month, year).get();
            if (action.getTypes() == TypesActions.IN) {
                report.setIn_total_value(report.getIn_total_value() + action.getAmount());
            } else {
                report.setOut_total_value(report.getOut_total_value() + action.getAmount());
            }
            report.setActions(actions);
            reportService.edit(report);
        }

        Report report = reportService.findByMonthAndYear(month, year).get();
        action.setReport(report);

        Action response = null;
        response = repository.save(action);
        return response;
    }

    public void delete(Long id) throws Exception {
        repository.deleteById(id);
    }

    public Action update(Action action) {
        LocalDateTime hours = repository.findById(action.getId()).get().getHours();
        action.setHours(hours);
        Action response = action;
        response = repository.save(action);
        return response;
    }

    public List<Action> getActionsByDateInterval(Interval interval) {
        List<Action> allActions = repository.findAll();
        List<Action> filteredActions = null;

        filteredActions = allActions.stream().filter(action -> {
            return action.getHours().isAfter(interval.getStart()) && action.getHours().isBefore(interval.getFinish());
        }).toList();

        return filteredActions;
    }

    public ApiResponse<List<Action>> getByDay(ActionCriteria criteria) {
        ApiResponse<List<Action>> response = new ApiResponse<>();
        List<Action> filteredActions = repository.findAll(createSpecification(criteria));
        return response.of(HttpStatus.OK, "Registros encontrados!", filteredActions);
    }

    private Specification<Action> createSpecification(ActionCriteria criteria) {
        Specification<Action> specification = Specification.where(null);
        if(criteria.getDate() != null) specification = specification.and(ActionCriteria.filterByDate(criteria.getDate()));
        if(criteria.getMonth() != null) specification = specification.and(ActionCriteria.filterByMonth(criteria.getMonth()));
        if(criteria.getYear() != null) specification = specification.and(ActionCriteria.filterByYear(criteria.getYear()));
        return specification;
    }
}
