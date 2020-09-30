/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteCambioEstadoDTO;

/**
 *
 * @author cfugu
 */
public interface ITramiteCambioEstadoService {

    public Optional<List<TramiteCambioEstadoDTO>> findAll();
    
    public Optional<List<TramiteCambioEstadoDTO>> findByTramiteRegistradId(Long id);

    public Optional<TramiteCambioEstadoDTO> findById(Long id);

    public TramiteCambioEstadoDTO create(TramiteCambioEstadoDTO tramiteRegistrado);

    public Optional<TramiteCambioEstadoDTO> update(TramiteCambioEstadoDTO tramiteRegistrado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
