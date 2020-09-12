/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.tramites.entities.Cliente;
import org.una.tramites.entities.TramiteTipo;

/**
 *
 * @author colo7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class TramiteRegistradoDTO {
    private Long id;
    private TramiteTipo tramitesTiposId;
    private Cliente clienteId;
    private List<NotaDTO> notas = new ArrayList<>();
    private List<ArchivoRelacionadoDTO> archivosRelacionados = new ArrayList<>();
    private List<RequisitoPresentadoDTO> requisitosPresentados = new ArrayList<>();
}