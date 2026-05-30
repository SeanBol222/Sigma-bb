package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.controller.global;

import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.ClientEquipmentFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.ClientNotFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.HeadquarterFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception.ServiceAreaFoundException;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.error_model.ClientErrorResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.controller.client_controller.ClientRestAdapter;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.controller.client_equipment_controller.ClientEquipmentRestAdapter;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.controller.headquarter_controller.HeadquarterRestAdapter;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.infrastructure.adapters.input.rest.controller.servie_area_controller.ServiceAreaRestAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

import static com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.utils.ClientErrorCatalog.*;
import static com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.utils.PersonErrorCatalog.UNKNOWN_ERROR;

/**
 * Manejador global de excepciones para el módulo de clientes.
 *
 * Esta clase centraliza el manejo de errores lanzados por los controladores,
 * permitiendo devolver respuestas consistentes y estructuradas (ErrorResponse).
 *
 * Está asociada al contexto "clientGlobalControllerAdvice", aplicando
 * sus manejadores únicamente a los controladores definidos en ese ámbito.
 *
 * Incluye el manejo de:
 * - Errores de validación de datos
 * - Recursos no encontrados
 * - Problemas de acceso a datos
 * - Errores de seguridad
 * - Excepciones genéricas
 */
@RestControllerAdvice(assignableTypes = {
        ClientRestAdapter.class,
        HeadquarterRestAdapter.class,
        ServiceAreaRestAdapter.class,
        ClientEquipmentRestAdapter.class
})
public class ClientGlobalControllerAdvice {

    /**
     * Maneja la excepción cuando no se encuentra un cliente.
     *
     * Retorna una respuesta de error con:
     * - Código de error.
     * - Mensaje descriptivo.
     * - Fecha y hora en que ocurrió el error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientNotFoundException.class)
    public ClientErrorResponse handleClientNotFoundException() {
        return ClientErrorResponse.builder()
                .code(CLIENT_NOT_FOUND.getCode())
                .message(CLIENT_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Maneja la excepción cuando no se encuentra una sede.
     *
     * Retorna una respuesta de error con:
     * - Código de error.
     * - Mensaje descriptivo.
     * - Fecha y hora en que ocurrió el error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HeadquarterFoundException.class)
    public ClientErrorResponse handleHeadquarterNotFoundException() {
        return ClientErrorResponse.builder()
                .code(HEADQUARTER_NOT_FOUND.getCode())
                .message(HEADQUARTER_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Maneja la excepción cuando no se encuentra un área de servicio.
     *
     * Retorna una respuesta de error con:
     * - Código de error.
     * - Mensaje descriptivo.
     * - Fecha y hora en que ocurrió el error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ServiceAreaFoundException.class)
    public ClientErrorResponse handleServiceAreaNotFoundException() {
        return ClientErrorResponse.builder()
                .code(SERVICE_AREA_NOT_FOUND.getCode())
                .message(SERVICE_AREA_NOT_FOUND.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Maneja la excepción cuando no se encuentra un equipo de cliente.
     *
     * Retorna una respuesta de error con:
     * - Código de error.
     * - Mensaje descriptivo.
     * - Fecha y hora en que ocurrió el error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClientEquipmentFoundException.class)
    public ClientErrorResponse handleClientEquipmentNotFoundException() {
        return ClientErrorResponse.builder()
                .code(CLIENT_EQUIPMENT_NOT_FOUND.getCode())
                .message(CLIENT_EQUIPMENT_NOT_FOUND.getMessage())
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
    public ClientErrorResponse handleGenericException(Exception ex) {

        ex.printStackTrace();

        Throwable rootCause = ex;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }

        return ClientErrorResponse.builder()
                .code(UNKNOWN_ERROR.getCode())
                .message(UNKNOWN_ERROR.getMessage())
                .details(List.of(
                        "Exception: " + ex.getClass().getName(),
                        "RootCause: " + rootCause.getClass().getName(),
                        "Message: " + rootCause.getMessage()
                ))
                .timestamp(LocalDateTime.now())
                .build();
    }

}
