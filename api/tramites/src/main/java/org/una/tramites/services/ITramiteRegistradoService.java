/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author colo7
 */

public interface ITramiteRegistradoService {
    public Optional<List<TramiteRegistradoDTO>> findAll();
    

    public Optional<TramiteRegistradoDTO> findById(Long id);
    
    public TramiteRegistradoDTO create(TramiteRegistradoDTO tramiteRegistrado);

    public Optional<TramiteRegistradoDTO> update(TramiteRegistradoDTO tramiteRegistrado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
