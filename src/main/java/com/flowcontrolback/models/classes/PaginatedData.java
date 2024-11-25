package com.flowcontrolback.models.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedData<T> {
    private List<T> content;
    private Pagination pagination;
}
