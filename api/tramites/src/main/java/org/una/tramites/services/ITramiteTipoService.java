/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.entities.TramiteTipo;

/**
 *
 * @author Bosco
 */
public interface ITramiteTipoService {
    
    public Optional<List<TramiteTipoDTO>> findAll();

    public Optional<TramiteTipoDTO> findById(Long id);

    public TramiteTipoDTO create(TramiteTipoDTO usuario);

    
    public Optional<List<TramiteTipoDTO>> findByEstado(boolean estado);
    
    public Optional<TramiteTipoDTO> update(TramiteTipoDTO usuario, Long id);

    public Optional<List<TramiteTipoDTO>> findByDepartamentoId(Long id);
    
    public void delete(Long id);

    public void deleteAll();
}
