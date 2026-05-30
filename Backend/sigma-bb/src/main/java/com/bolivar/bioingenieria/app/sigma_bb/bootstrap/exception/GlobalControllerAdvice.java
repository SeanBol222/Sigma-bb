package com.bolivar.bioingenieria.app.sigma_bb.bootstrap.exception;

import com.bolivar.bioingenieria.app.sigma_bb.bootstrap.exception.response.GlobalErrorResponse;
import com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.error_model.ClientErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.bolivar.bioingenieria.app.sigma_bb.bootstrap.exception.utils.GlobalErrorCatalog.*;

/**
 * Clase de asesoramiento global para el manejo de excepciones en los controladores.
 *
 * <p>Esta clase utiliza la anotación {@code @RestControllerAdvice} para interceptar
 * y manejar excepciones lanzadas por los controladores de la aplicación, proporcionando
 * respuestas estructuradas y consistentes para diferentes tipos de errores.</p>
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Maneja errores relacionados con el acceso a la base de datos.
     *
     * Se ejecuta cuando ocurre una {@link DataAccessException} y retorna
     * una respuesta con:
     * - Código de error.
     * - Mensaje general de fallo en base de datos.
     * - Lista de detalles vacía.
     * - Fecha y hora del error.
     *
     * @return ErrorResponse con la información del error.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public GlobalErrorResponse handleDataAccessException(DataAccessException ex) {
        return GlobalErrorResponse.builder()
                .code(DATABASE_ERROR.getCode())
                .message(DATABASE_ERROR.getMessage())
                .details(List.of(ex.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
    }

    /**
     * Maneja errores de validación de datos en las solicitudes.
     *
     * Se ejecuta cuando ocurre una {@link MethodArgumentNotValidException} debido a
     * fallos en la validación de los datos de entrada, retornando una respuesta con:
     * - Código de error
     * - Mensaje general de error de validación
     * - Lista de detalles con los mensajes específicos de cada campo que falló la validación
     * - Fecha y hora en que ocurrió el error
     *
     * @return ErrorResponse con la información del error
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ClientErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();

        return ClientErrorResponse.builder()
                .code(INVALID_DATA.getCode())
                .message(INVALID_DATA.getMessage())
                .details(result.getFieldErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()))
                .timestamp(LocalDateTime.now())
                .build();

    }

}
