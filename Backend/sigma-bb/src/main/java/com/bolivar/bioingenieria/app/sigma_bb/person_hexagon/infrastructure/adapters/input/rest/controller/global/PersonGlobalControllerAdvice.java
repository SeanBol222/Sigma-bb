package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.controller.global;

import com.bolivar.bioingenieria.app.sigma_bb.bootstrap.exception.response.GlobalErrorResponse;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.exception.*;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.model.error_model.PersonErrorResponse;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.controller.EmailPersonRestAdapter;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.controller.PersonRestAdapter;
import com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.infrastructure.adapters.input.rest.controller.PhonePersonRestAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.utils.PersonErrorCatalog.*;

/**
 * Manejador global de excepciones para el módulo de personas.
 *
 * Esta clase centraliza el manejo de errores lanzados por los controladores,
 * permitiendo devolver respuestas consistentes y estructuradas (ErrorResponse).
 *
 * Está asociada al contexto "personGlobalControllerAdvice", aplicando
 * sus manejadores únicamente a los controladores definidos en ese ámbito.
 *
 * Incluye el manejo de:
 * - Errores de validación de datos
 * - Recursos no encontrados
 * - Problemas de acceso a datos
 * - Errores de seguridad
 * - Excepciones genéricas
 */
@RestControllerAdvice(
        assignableTypes = {
            PersonRestAdapter.class,
            PhonePersonRestAdapter.class,
            EmailPersonRestAdapter.class
        }
)
public class PersonGlobalControllerAdvice {

    /**
     * Maneja la excepción cuando no se encuentra una persona.
     *
     * Retorna una respuesta de error con:
     * - Código de error.
     * - Mensaje descriptivo.
     * - Fecha y hora en que ocurrió el error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PersonNotFoundException.class)
    public PersonErrorResponse handlePersonNotFoundException() {
        return PersonErrorResponse.builder()
                .code(PERSON_NOT_FOUND.getCode())
                .message(PERSON_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Maneja excepciones genéricas no controladas en la aplicación.
     *
     * Se ejecuta cuando ocurre cualquier excepción no manejada específicamente,
     * retornando una respuesta con estado HTTP 500 (INTERNAL_SERVER_ERROR) que incluye:
     * - Código de error
     * - Mensaje general
     * - Lista de detalles vacía
     * - Fecha y hora en que ocurrió el error
     *
     * @return ErrorResponse con la información del error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GlobalErrorResponse handleGenericException(Exception ex) {
        return GlobalErrorResponse.builder()
                .code(UNKNOWN_ERROR.getCode())
                .message(UNKNOWN_ERROR.getMessage())
                .details(Collections.singletonList(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Maneja excepciones relacionadas con la existencia previa de un usuario en Keycloak.
     *
     * Se ejecuta cuando ocurre una {@link KeycloakUserAlreadyExistsException} y retorna
     * una respuesta con estado HTTP 409 (CONFLICT) que incluye:
     * - Código de error específico para usuario ya existente en Keycloak.
     * - Mensaje descriptivo del error.
     * - Lista de detalles vacía.
     * - Fecha y hora en que ocurrió el error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(KeycloakUserAlreadyExistsException.class)
    public PersonErrorResponse handleKeycloakUserAlreadyExistsException() {
        return PersonErrorResponse.builder()
                .code(KEYCLOAK_USER_ALREADY_EXISTS.getCode())
                .message(KEYCLOAK_USER_ALREADY_EXISTS.getMessage())
                .details(Collections.emptyList())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Maneja excepciones relacionadas con datos inválidos en la integración de Keycloak.
     *
     * Se ejecuta cuando ocurre una {@link KeycloakInvalidDataException} y retorna
     * una respuesta con estado HTTP 400 (BAD_REQUEST) que incluye:
     * - Código de error específico para datos inválidos en Keycloak.
     * - Mensaje descriptivo del error.
     * - Lista de detalles vacía.
     * - Fecha y hora en que ocurrió el error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(KeycloakInvalidDataException.class)
    public PersonErrorResponse handleKeycloakInvalidDataException() {
        return PersonErrorResponse.builder()
                .code(KEYCLOAK_INVALID_DATA.getCode())
                .message(KEYCLOAK_INVALID_DATA.getMessage())
                .details(Collections.emptyList())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Maneja excepciones relacionadas con la falta de autorización en Keycloak.
     *
     * Se ejecuta cuando ocurre una {@link KeycloakUnauthorizedException} y retorna
     * una respuesta con estado HTTP 401 (UNAUTHORIZED) que incluye:
     * - Código de error específico para falta de autorización en Keycloak.
     * - Mensaje descriptivo del error.
     * - Lista de detalles vacía.
     * - Fecha y hora en que ocurrió el error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(KeycloakUnauthorizedException.class)
    public PersonErrorResponse handleKeycloakUnauthorizedException() {
        return PersonErrorResponse.builder()
                .code(KEYCLOAK_UNAUTHORIZED.getCode())
                .message(KEYCLOAK_UNAUTHORIZED.getMessage())
                .details(Collections.emptyList())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Maneja excepciones relacionadas con problemas de conexión a Keycloak.
     *
     * Se ejecuta cuando ocurre una {@link KeycloakConnectionException} y retorna
     * una respuesta con estado HTTP 503 (SERVICE_UNAVAILABLE) que incluye:
     * - Código de error específico para problemas de conexión a Keycloak.
     * - Mensaje descriptivo del error.
     * - Lista de detalles vacía.
     * - Fecha y hora en que ocurrió el error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(KeycloakConnectionException.class)
    public PersonErrorResponse handleKeycloakConnectionException() {
        return PersonErrorResponse.builder()
                .code(KEYCLOAK_CONNECTION_ERROR.getCode())
                .message(KEYCLOAK_CONNECTION_ERROR.getMessage())
                .details(Collections.emptyList())
                .timestamp(LocalDateTime.now())
                .build();
    }

}
