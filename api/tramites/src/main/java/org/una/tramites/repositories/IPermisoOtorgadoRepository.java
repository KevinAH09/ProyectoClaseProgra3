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
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.PermisosOtorgados;

/**
 *
 * @author Bosco
 */
public interface IPermisoOtorgadoRepository extends JpaRepository<PermisosOtorgados, Long> {

    public Optional<List<PermisosOtorgados>> findByUsuarioId(@Param("usuario") Long usuario);

    public Optional<List<PermisosOtorgados>> findByPermisoId(@Param("permisosId") Long permisos);

    public Optional<List<PermisosOtorgados>> findByUsuarioIdAndEstado(@Param("usuario") Long usuario, @Param("estado") boolean estado);

    public Optional<List<PermisosOtorgados>> findByPermisoIdAndEstado(@Param("permisosId") Long permisos, @Param("estado") boolean estado);

    public Optional<List<PermisosOtorgados>> findByFechaRegistroBetween(@Param("fechaRegistro") Date fechaRegistro, @Param("fechaRegistro1") Date fechaRegistro1);

}
