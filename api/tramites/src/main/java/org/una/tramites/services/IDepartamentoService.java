/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Departamento;

/**
 *
 * @author colo7
 */
public interface IDepartamentoService {

    public Optional<List<DepartamentoDTO>> findAll();

    public Optional<DepartamentoDTO> findById(Long id);
    
    public Optional<List<DepartamentoDTO>> findByEstadoContaining(boolean estado);

    public Optional<List<DepartamentoDTO>> findByNombreAproximateIgnoreCase(String nombre);

    public DepartamentoDTO create(DepartamentoDTO departamento);

    public Optional<DepartamentoDTO> update(DepartamentoDTO departamento, Long id);
}
