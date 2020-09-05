/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author colo7
 */

public interface ITramiteRegistradoService {
    public Optional<List<TramiteRegistrado>> findAll();

    public Optional<TramiteRegistrado> findById(Long id);
    
    public TramiteRegistrado create(TramiteRegistrado tramiteRegistrado);

    public Optional<TramiteRegistrado> update(TramiteRegistrado tramiteRegistrado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
