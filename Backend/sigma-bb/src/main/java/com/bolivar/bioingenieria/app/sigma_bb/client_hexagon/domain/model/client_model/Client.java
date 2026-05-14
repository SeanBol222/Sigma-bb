package com.bolivar.bioingenieria.app.sigma_bb.client_hexagon.domain.model.client_model;

import lombok.*;

import java.util.List;

/**
 * Modelo de dominio que representa un cliente (empresa o persona jurídica).
 *
 * Contiene los datos principales necesarios para identificar y gestionar
 * a un cliente dentro del dominio del módulo "client_hexagon".
 *
 * Campos clave:
 * - identificadorCliente: identificador único del cliente (string, podría ser NIT o similar)
 * - tipoIdentifiacion: tipo de documento o categoría de identificación
 * - razonSocial: nombre o razón social del cliente
 * - estadoActivo: si el cliente está activo en el sistema
 * - correosCliente / telefonosCliente: contactos asociados
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

    /**
     * Identificador único del cliente. Puede ser un NIT, código o clave definida por el negocio.
     */
    private String identificadorCliente;

    /**
     * Tipo de identificación asociado al cliente (por ejemplo: NIT, Cédula, Pasaporte).
     */
    private String tipoIdentifiacion;

    /**
     * Razón social o nombre comercial del cliente.
     */
    private String razonSocial;

    /**
     * Indica si el cliente está activo en el sistema. Usado para filtrado lógico (soft-delete).
     */
    private boolean estadoActivo;

    /**
     * Lista de {@link EmailClient} asociados al cliente.
     */
    private List<EmailClient> correosCliente;

    /**
     * Lista de {@link PhoneClient} asociados al cliente.
     */
    private List<PhoneClient> telefonosCliente;
}
