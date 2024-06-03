package com.flowcontrolback.components.actions;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ActionCriteria {

    private Integer date;
    private Integer month;
    private Integer year;

    public static Specification<Action> filterByDate(Integer date) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("hours"), "%-" + date + " %"
                ));
    }

    public static Specification<Action> filterByMonth(Integer month) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("hours"), "%-" + month+1 + "-%"
                ));
    }

    public static Specification<Action> filterByYear(Integer year) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("hours"), "%" + year + "-%"
                ));
    }
}
