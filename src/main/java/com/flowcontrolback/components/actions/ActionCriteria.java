package com.flowcontrolback.components.actions;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@Data
public class ActionCriteria {

    private String date;

    public static Specification<Action> filterByDate(String date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(
                        root.get("date"), LocalDateTime.parse(date), LocalDateTime.parse(date).plusDays(1)
                );
    }
}
