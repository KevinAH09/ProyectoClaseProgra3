/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.Date;

/**
 *
 * @author Bosco
 */
public class VariacionDTO {
    private Long id;
    private String descripcion;
    private Long grupo;
    private boolean estado;
    private Date fechaRegistro;
    private TramiteTipoDTO tramiteTipo;
    
}
