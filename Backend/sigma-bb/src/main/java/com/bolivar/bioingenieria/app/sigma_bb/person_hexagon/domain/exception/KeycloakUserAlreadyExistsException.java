package com.bolivar.bioingenieria.app.sigma_bb.person_hexagon.domain.exception;

/**
 * Excepción lanzada cuando se intenta crear un usuario en Keycloak con un nombre de usuario que ya existe.
 * Esta excepción se dispara en el proceso de creación de usuarios en Keycloak cuando el nombre de usuario proporcionado
 * ya está registrado en el sistema, lo que impide la creación de un nuevo usuario con ese nombre.
 */
public class KeycloakUserAlreadyExistsException extends RuntimeException{
}
