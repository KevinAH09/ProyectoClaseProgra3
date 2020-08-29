/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.PermisosOtorgados;

/**
 *
 * @author cfugu
 */
public interface IPermisosOtorgadosRepository {

    public Optional<PermisosOtorgados> findById(Long usuarioId);

    public Optional<List<PermisosOtorgados>> findByUsuarioId(Long usuarioId);

    public Optional<List<PermisosOtorgados>> findByPermisoId(Long permisoId);

    public Optional<List<PermisosOtorgados>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);

    public Optional<List<PermisosOtorgados>> findByPermisoIdAndEstado(Long permisoId, boolean estado);

    public Optional<List<PermisosOtorgados>> findByFechaRegistroBetween(Date startDate, Date endDate);
}
