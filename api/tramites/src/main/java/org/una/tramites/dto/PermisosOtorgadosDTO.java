/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.Date;
import java.util.List;
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
public class PermisosOtorgadosDTO {

    private Long id;
    private boolean estado;
    private Date fechaRegistro;
    private PermisoDTO permisoId;
    private UsuarioDTO usuarios;
}