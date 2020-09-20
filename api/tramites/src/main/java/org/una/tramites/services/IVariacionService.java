/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.Variacion;

/**
 *
 * @author Bosco
 */
public interface IVariacionService {
    
     public Optional<List<VariacionDTO>> findAll();

    public Optional<VariacionDTO> findById(Long id);

    public Optional<List<VariacionDTO>> findByEstadoContaining(boolean estado);
    
    public VariacionDTO create(VariacionDTO usuario);

    public Optional<VariacionDTO> update(VariacionDTO variacionDTO, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional <List<VariacionDTO>>findByTramiteTipoId(Long id);
}
