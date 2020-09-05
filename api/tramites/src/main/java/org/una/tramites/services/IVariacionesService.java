/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Variaciones;

/**
 *
 * @author Bosco
 */
public interface IVariacionesService {
     public Optional<List<Variaciones>> findAll();

    public Optional<Variaciones> findById(Long id);

    public Optional<List<Variaciones>> findByEstadoContaining(boolean estado);
    
    public Variaciones create(Variaciones usuario);

    public Optional<Variaciones> update(Variaciones usuario, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional <List<Variaciones>>findByTramiteTipoId(Long id);
}
