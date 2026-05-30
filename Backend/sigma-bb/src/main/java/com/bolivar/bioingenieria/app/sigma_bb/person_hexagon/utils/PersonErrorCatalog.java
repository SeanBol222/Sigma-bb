package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.utils;

import lombok.Getter;

/**
 * Catálogo centralizado de códigos y mensajes de error para el hexágono de personas.
 *
 * Define un conjunto de errores predefinidos que pueden ocurrir en las operaciones
 * relacionadas con la gestión de personas. Cada error contiene un código único y un
 * mensaje descriptivo para facilitar el diagnóstico y la depuración.
 */
@Getter
public enum PersonErrorCatalog {
    /**
     * Error cuando una persona no es encontrada en la base de datos.
     * Código: ERR_PERSON_001
     */
    PERSON_NOT_FOUND("ERR_PERSON_001", "Person not found"),

    /**
     * Error desconocido que no corresponde a ninguno de los casos específicos
     * definidos en el catálogo.
     * Código: ERR_UNKNOW_999
     */
    UNKNOWN_ERROR("ERR_UNKNOW_999", "Unknown error"),

    /**
     * Error de validación de datos cuando los datos proporcionados no cumplen
     * con los requisitos esperados.
     * Código: ERR_VALIDATION_422
     */
    KEYCLOAK_USER_ALREADY_EXISTS("ERR_KEYCLOAK_001", "Keycloak user already exists"),

    /**
     * Error de validación de datos cuando los datos proporcionados para una operación
     * en Keycloak son inválidos o no cumplen con los requisitos esperados.
     * Código: ERR_KEYCLOAK_002
     */
    KEYCLOAK_INVALID_DATA("ERR_KEYCLOAK_002", "Invalid data for Keycloak operation"),

    /**
     * Error de autenticación y autorización específico para Keycloak cuando el acceso
     * a Keycloak es denegado debido a credenciales inválidas o falta de permisos.
     * Código: ERR_KEYCLOAK_003
     */
    KEYCLOAK_UNAUTHORIZED("ERR_KEYCLOAK_003", "Unauthorized access to Keycloak"),

    /**
     * Error de conexión específico para Keycloak cuando hay problemas de red o el servicio
     * de Keycloak no está disponible.
     * Código: ERR_KEYCLOAK_004
     */
    KEYCLOAK_CONNECTION_ERROR("ERR_KEYCLOAK_004", "Connection error with Keycloak");

    private final String code;
    private final String message;

    /**
     * Constructor del enumerado ErrorCatalog.
     *
     * @param code Código único del error para identificación
     * @param message Mensaje descriptivo del error en inglés
     */
    PersonErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
