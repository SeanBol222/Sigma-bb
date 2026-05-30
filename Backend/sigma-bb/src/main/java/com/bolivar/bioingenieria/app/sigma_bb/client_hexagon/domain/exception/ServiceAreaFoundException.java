package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception;

/**
 * Excepción personalizada que se lanza cuando se encuentra un área de servicio (service area) que ya existe en el sistema.
 * Esta excepción es utilizada para indicar que la operación de creación o actualización de un área de servicio no puede ser completada
 * debido a la existencia previa de un área de servicio con los mismos atributos únicos (por ejemplo, nombre o código).
 */
public class ServiceAreaFoundException extends RuntimeException{
}
