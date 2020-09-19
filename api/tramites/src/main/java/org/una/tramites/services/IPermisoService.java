/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.entities.Permiso;

/**
 *
 * @author cfugu
 */
public interface IPermisoService {

    public Optional<List<PermisoDTO>> findAll();

    public Optional<PermisoDTO> findById(Long id);
    
    public Optional<PermisoDTO> findByCodigo(String codigo);

    public PermisoDTO create(PermisoDTO permiso);

    public Optional<PermisoDTO> update(PermisoDTO permiso, Long id);

    public Optional<List<PermisoDTO>> findByEstado(boolean estado);
    
    public Long countByEstado(boolean estado);

    public Optional<List<PermisoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate);//Crear query

    public void delete(Long id);

    public void deleteAll();

}
