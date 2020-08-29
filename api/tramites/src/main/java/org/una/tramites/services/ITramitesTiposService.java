/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramitesTipos;

/**
 *
 * @author Bosco
 */
public interface ITramitesTiposService {
    
    public Optional<List<TramitesTipos>> findAll();

    public Optional<TramitesTipos> findById(Long id);

    public TramitesTipos create(TramitesTipos usuario);

    public Optional<List<TramitesTipos>> findByEstadoContaining(boolean estado);
    
    public Optional<TramitesTipos> update(TramitesTipos usuario, Long id);

    public Optional<List<TramitesTipos>> findByDepartamentoId(Long id);
}
