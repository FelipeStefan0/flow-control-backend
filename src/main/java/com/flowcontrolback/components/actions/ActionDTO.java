package com.flowcontrolback.components.actions;

import com.flowcontrolback.models.enums.TypesActions;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record ActionDTO(

        @NotEmpty(message = "Valor não pode ser vazio")
        Double value,

        @NotEmpty(message = "Data não pode ser vazia")
        LocalDateTime date,

        String description,

        @NotEmpty(message = "Tipo não pode ser vazio")
        TypesActions type
) {
}
