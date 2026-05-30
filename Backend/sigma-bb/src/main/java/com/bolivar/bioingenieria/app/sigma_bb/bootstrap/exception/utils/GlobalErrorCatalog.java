package com.bolivar.bioingenieria.app.sigma_bb.bootstrap.exception.utils;

import lombok.Getter;

/**
 * Enumerado que define un catálogo de errores globales para la aplicación.
 *
 * <p>Cada constante representa un error específico que puede ocurrir en el sistema,
 * con un código único y un mensaje descriptivo asociado.</p>
 */
@Getter
public enum GlobalErrorCatalog {

    /**
     * Error genérico de base de datos que puede ocurrir durante operaciones
     * de persistencia.
     * Código: ERR_DATABASE_003
     */
    DATABASE_ERROR("ERR_DATABASE_001", "Database error"),

    /**
    * Error de validación de datos que ocurre cuando los datos de entrada no
    * cumplen con los requisitos esperados.
    * Código: ERR_INVALID_DATA_002
    */
    INVALID_DATA("ERR_INVALID_DATA", "Invalid data");

    private final String code;
    private final String message;

    /**
     * Constructor del enumerado ErrorCatalog.
     *
     * @param code Código único del error para identificación
     * @param message Mensaje descriptivo del error en inglés
     */
    GlobalErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
