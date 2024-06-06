package com.flowcontrolback.components.actions;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ActionCriteria {

    private Integer day;
    private Integer month;
    private Integer year;

    public static Specification<Action> filterByDate(Integer day) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("date"), "%-" + day + " %"
                ));
    }

    public static Specification<Action> filterByMonth(Integer month) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("date"), "%-" + month+1 + "-%"
                ));
    }

    public static Specification<Action> filterByYear(Integer year) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        root.get("date"), "%" + year + "-%"
                ));
    }
}
