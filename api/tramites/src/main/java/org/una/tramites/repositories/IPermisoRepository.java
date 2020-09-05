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
import org.una.tramites.entities.Permiso;

/**
 *
 * @author cfugu
 */
public interface IPermisoRepository extends JpaRepository<Permiso, Long> {

    public List<Permiso> findByEstadoContaining(boolean estado);

    public List<Permiso> findByCodigoIgnoreCase(String codigo);

    public Optional<Permiso> findById(Long id);
    
    public Optional<List<Permiso>> findByFechaRegistroBetween(Date startDate, Date endDate);
}
