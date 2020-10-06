/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.NotaDTO;

/**
 *
 * @author colo7
 */
public interface INotaService {
    public Optional<List<NotaDTO>> findAll();

    public Optional<NotaDTO> findById(Long id);

    public NotaDTO create(NotaDTO variacion);

    public Optional<NotaDTO> update(NotaDTO nota, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<NotaDTO> findByTitulo(String grupo);
    
    public Optional<List<NotaDTO>> findByRegistroIdImplementado(Long tramiteRegistradoId);

}
