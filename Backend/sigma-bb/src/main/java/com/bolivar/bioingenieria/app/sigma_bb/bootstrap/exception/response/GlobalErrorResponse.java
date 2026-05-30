package com.bolivar.bioingenieria.app.sigma_bb.bootstrap.exception.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class GlobalErrorResponse {

    /**
     * Código único del error para identificarlo en el catálogo de errores.
     */
    private String code;

    /**
     * Mensaje descriptivo del error.
     */
    private String message;

    /**
     * Lista de detalles adicionales o mensajes específicos sobre el error.
     */
    private List<String> details;

    /**
     * Fecha y hora en que ocurrió el error.
     */
    private LocalDateTime timestamp;
}
