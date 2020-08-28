/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.PermisosOtorgados;

/**
 *
 * @author cfugu
 */
public interface IPermisosOtorgadosService {

    public Optional<PermisosOtorgados> findById(Long usuarioId);

    public Optional<List<PermisosOtorgados>> findByUsuarioId(Long usuarioId);

    public Optional<List<PermisosOtorgados>> findByPermisoId(Long permisoId);

    public Optional<List<PermisosOtorgados>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);

    public Optional<List<PermisosOtorgados>> findByPermisoIdAndEstado(Long permisoId, boolean estado);

    public Optional<List<PermisosOtorgados>> findByFechaRegistroBetween(Date startDate, Date endDate);

    public PermisosOtorgados create(PermisosOtorgados permisoOtorgado);

    public Optional<PermisosOtorgados> update(PermisosOtorgados permisoOtorgado, Long id);

    public void delete(Long id);

    public void deleteAll();

}
