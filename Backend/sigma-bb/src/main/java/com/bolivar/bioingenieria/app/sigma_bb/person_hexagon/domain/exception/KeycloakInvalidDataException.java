package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.exception;

/**
 * Excepción lanzada cuando se intenta realizar una operación con datos inválidos en Keycloak.
 * Esta excepción se dispara en casos donde los datos proporcionados para la creación o actualización
 * de usuarios en Keycloak no cumplen con los requisitos o formatos esperados, lo que impide la correcta
 * ejecución de la operación en el sistema de gestión de identidades.
 */
public class KeycloakInvalidDataException extends RuntimeException{
}
