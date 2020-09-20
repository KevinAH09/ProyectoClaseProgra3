/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;

/**
 *
 * @author colo7
 */
public interface IRequisitoService {

    public Optional<List<RequisitoDTO>> findAll();

    public Optional<RequisitoDTO> findById(Long id);

    public Optional<List<RequisitoDTO>> findByEstadoContaining(boolean estado);

    public RequisitoDTO create(RequisitoDTO requisitos);

    public Optional<RequisitoDTO> update(RequisitoDTO requisitos, Long id);

    public void delete(Long id);

    public void deleteAll();
}
