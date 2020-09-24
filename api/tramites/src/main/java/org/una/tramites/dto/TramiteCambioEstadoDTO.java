/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author cfugu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteCambioEstadoDTO {

    private Long id;
    private Date fechaRegistro;
    private ClienteDTO cliente;
    private TramiteEstadoDTO tramiteEstado;
    private TramiteRegistradoDTO tramiteRegistrado;

}
