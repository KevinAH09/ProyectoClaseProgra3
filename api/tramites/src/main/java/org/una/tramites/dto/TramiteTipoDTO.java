/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author Bosco
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteTipoDTO {

    private Long id;
    private String descripcion;
    private boolean estado;
    private DepartamentoDTO departamento;
    private Date fechaRegistro;
    private Date fechaModificacion;
    private List<TramiteRegistrado> tramiteRegistrado = new ArrayList<>();

}
