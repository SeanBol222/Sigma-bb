package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.exception;

/**
 * Excepción personalizada que se lanza cuando se encuentra una sede (headquarter) que ya existe en el sistema.
 * Esta excepción es utilizada para indicar que la operación de creación o actualización de una sede no puede ser completada
 * debido a la existencia previa de una sede con los mismos atributos únicos (por ejemplo, nombre o dirección).
 */
public class HeadquarterFoundException extends RuntimeException{
}
