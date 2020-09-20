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
import org.una.tramites.entities.PermisoOtorgado;

/**
 *
 * @author Bosco
 */
public interface IPermisoOtorgadoRepository extends JpaRepository<PermisoOtorgado, Long> {

    public List<PermisoOtorgado> findByUsuarioId(@Param("usuario") Long usuario);

    public List<PermisoOtorgado> findByPermisoId(@Param("permisosId") Long permisos);

    public List<PermisoOtorgado> findByUsuarioIdAndEstado(@Param("usuario") Long usuario, @Param("estado") boolean estado);

    public List<PermisoOtorgado> findByPermisoIdAndEstado(@Param("permisosId") Long permisos, @Param("estado") boolean estado);

    public List<PermisoOtorgado> findByFechaRegistroBetween(@Param("fechaRegistro") Date fechaRegistro, @Param("fechaRegistro1") Date fechaRegistro1);

}
