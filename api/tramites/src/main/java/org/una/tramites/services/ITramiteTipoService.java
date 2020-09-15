/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramiteTipo;

/**
 *
 * @author Bosco
 */
public interface ITramiteTipoService {
    
    public Optional<List<TramiteTipo>> findAll();

    public Optional<TramiteTipo> findById(Long id);

    public TramiteTipo create(TramiteTipo usuario);

    
    public Optional<List<TramiteTipo>> findByEstado(boolean estado);
    
    public Optional<TramiteTipo> update(TramiteTipo usuario, Long id);

    public Optional<List<TramiteTipo>> findByDepartamentoId(Long id);
    
    public void delete(Long id);

    public void deleteAll();
}
