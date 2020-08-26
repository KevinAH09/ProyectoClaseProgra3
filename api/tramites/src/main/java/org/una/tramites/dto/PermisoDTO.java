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
public class PermisoDTO {
    private Long id;
    private String codigo;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;
    private Date fechaModificacion;
}
