package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.client_model;

import lombok.*;

/**
 * Modelo de dominio que representa un correo electrónico asociado a un cliente.
 *
 * Contiene la dirección de correo y su estado lógico de actividad dentro del sistema.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailClient {

    /**
     * Identificador del correo del cliente. Puede ser una clave interna o cadena única.
     */
    private String idCorreoCliente;

    /**
     * Dirección de correo electrónico del cliente.
     */
    private String correoCliente;

    /**
     * Indica si el correo está activo en el sistema (soft delete).
     */
    private boolean estadoActivo;

}
