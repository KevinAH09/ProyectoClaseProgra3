/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.PermisoOtorgado;

/**
 *
 * @author Bosco
 */
public interface IPermisoOtorgadoService {

    
    public Optional <PermisoOtorgado> findById(Long usuarioId);

    public Optional<List<PermisoOtorgado>> findByUsuarioId(Long usuarioId);

    public Optional<List<PermisoOtorgado>> findByPermisoId(Long permisoId);

    public Optional<List<PermisoOtorgado>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);

    public Optional<List<PermisoOtorgado>> findByPermisoIdAndEstado(Long permisoId, boolean estado);

    public Optional<List<PermisoOtorgado>> findByFechaRegistroBetween(Date startDate, Date endDate);
 
    public PermisoOtorgado create(PermisoOtorgado permisoOtorgado);

    public Optional<PermisoOtorgado> update(PermisoOtorgado permisoOtorgado, Long id);

    public void delete(Long id);

    public void deleteAll();



}
