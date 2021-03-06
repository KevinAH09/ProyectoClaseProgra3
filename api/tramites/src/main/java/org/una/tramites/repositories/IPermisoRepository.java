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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.Permiso;

/**
 *
 * @author cfugu
 */
public interface IPermisoRepository extends JpaRepository<Permiso, Long> {
    @Query("select u from Permiso u where u.estado=estado")
    public List<Permiso> findByEstadoContaining(@Param("estado")boolean estado);

    public Permiso findByCodigo(String codigo);

    @Query("select COUNT(*) from Permiso u where u.estado=estado")
    public Long countByEstado(@Param("estado") boolean estado);

    public Optional<Permiso> findById(Long id);
    
    public List<Permiso> findByEstado(boolean estado);
    
    public List<Permiso> findByFechaRegistroBetween(Date startDate, Date endDate);
}
