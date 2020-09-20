/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.TramiteEstadoDTO;

/**
 *
 * @author colo7
 */
public interface ITramiteEstadoService {

    public Optional<TramiteEstadoDTO> findById(Long id);
    
    public Optional<TramiteEstadoDTO> findByNombre(String nombre);

    public Optional<List<TramiteEstadoDTO>> findAll();

    public TramiteEstadoDTO create(TramiteEstadoDTO tramite);

    public Optional<TramiteEstadoDTO> update(TramiteEstadoDTO tramite, Long id);

    public void delete(Long id);

    public void deleteAll();
}
