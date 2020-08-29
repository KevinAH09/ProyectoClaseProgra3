/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Permisos;
import org.una.tramites.entities.PermisosOtorgados;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author cfugu
 */
public interface IPermisosOtorgadosRepository  extends JpaRepository<PermisosOtorgados, Long> {

    //public Optional<PermisosOtorgados> findById(Long id);

    public Optional<List<PermisosOtorgados>> findByUsuarioId(Long id);

    public Optional<List<PermisosOtorgados>> findByPermisoId(Long id);

    //public Optional<List<PermisosOtorgados>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);

    //public Optional<List<PermisosOtorgados>> findByPermisoIdAndEstado(Long permisoId, boolean estado);

   // public Optional<List<PermisosOtorgados>> findByFechaRegistroBetween(Date startDate, Date endDate);
}
