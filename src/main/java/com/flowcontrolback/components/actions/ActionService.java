package com.flowcontrolback.components.actions;

import com.flowcontrolback.components.report.Report;
import com.flowcontrolback.components.report.ReportService;
import com.flowcontrolback.models.classes.ApiResponse;
import com.flowcontrolback.models.classes.Interval;
import com.flowcontrolback.models.enums.TypesActions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActionService {

    private final ActionRepository repository;
    private final ReportService reportService;

    public List<Action> list(ActionCriteria criteria) {
        List<Action> response = null;
        response = repository.findAll(createSpecification(criteria));
        return response;
    }
    
    public Action create(Action action) {
        Integer year = action.getDate().getYear();
        Month month = action.getDate().getMonth();
        List<Action> actions = new ArrayList<>();
        if(reportService.findByMonthAndYear(month, year).isEmpty()) {
            Report report = Report.builder()
                    .month(month)
                    .year(year)
                    .total_value(0.0)
                    .build();
            if (action.getType() == TypesActions.IN) {
                report.setIn_total_value(action.getValue());
                report.setOut_total_value(0.0);
            } else {
                report.setOut_total_value(action.getValue());
                report.setIn_total_value(0.0);
            }
            report.setActions(actions);
            reportService.create(report);
        } else {
            Report report = reportService.findByMonthAndYear(month, year).get();
            if (action.getType() == TypesActions.IN) {
                report.setIn_total_value(report.getIn_total_value() + action.getValue());
            } else {
                report.setOut_total_value(report.getOut_total_value() + action.getValue());
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

    public void delete(Long id) {
        Optional<Action> foundAction;
        Report report;
        if (repository.findById(id).isPresent()) {
            foundAction = repository.findById(id);
            report = foundAction.get().getReport();

            if(foundAction.get().getType() == TypesActions.IN) {
                report.setTotal_value(report.getTotal_value() - foundAction.get().getValue());
                report.setIn_total_value(report.getIn_total_value() - foundAction.get().getValue());
            } else {
                report.setTotal_value(report.getTotal_value() + foundAction.get().getValue());
                report.setOut_total_value(report.getOut_total_value() - foundAction.get().getValue());
            }

            foundAction.get().getReport().getActions().remove(foundAction.get());
            foundAction.get().setReport(null);
            reportService.edit(report);
            repository.deleteById(id);
        }
        else throw new RuntimeException("Id não encontrado!");
    }

    public Action update(Long id, Action action) {
        Optional<Action> currentAction = repository.findById(id);
        Report report;
        Action response = new Action();
        if (currentAction.isPresent()) {
            report = currentAction.get().getReport();

//            if(!currentAction.get().getType().equals(action.getType())) {
//                if (action.getType().equals(TypesActions.IN)) {
//                    report.setIn_total_value(report.getIn_total_value() + action.getValue());
//                    report.setOut_total_value(report.getOut_total_value() - currentAction.get().getValue());
//                } else {
//                    report.setOut_total_value(report.getIn_total_value() + action.getValue());
//                    report.setIn_total_value(report.getOut_total_value() - currentAction.get().getValue());
//                }
//            } else {
//                if (action.getType().equals(TypesActions.IN)) {
//                    report.setIn_total_value(report.getIn_total_value() + action.getValue() - currentAction.get().getValue());
//                } else {
//                    report.setOut_total_value(report.getOut_total_value() + action.getValue() - currentAction.get().getValue());
//                }
//            }

            currentAction.get().setValue(action.getValue());
            currentAction.get().setDate(action.getDate());
            currentAction.get().setDescription(action.getDescription());
            currentAction.get().setType(action.getType());

            reportService.edit(report);
            response = repository.save(currentAction.get());
        }
        return response;
    }

    public List<Action> getActionsByDateInterval(Interval interval) {
        List<Action> allActions = repository.findAll();
        List<Action> filteredActions = null;

        filteredActions = allActions.stream().filter(action -> {
            return action.getDate().isAfter(interval.getStart()) && action.getDate().isBefore(interval.getFinish());
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
//        if(criteria.getDay() != null) specification = specification.and(ActionCriteria.filterByDate(criteria.getDay()));
//        if(criteria.getMonth() != null) specification = specification.and(ActionCriteria.filterByMonth(criteria.getMonth()));
//        if(criteria.getYear() != null) specification = specification.and(ActionCriteria.filterByYear(criteria.getYear()));
        return specification;
    }
}
