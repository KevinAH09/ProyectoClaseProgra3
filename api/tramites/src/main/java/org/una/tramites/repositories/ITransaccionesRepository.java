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
import org.una.tramites.entities.Transacciones;

/**
 *
 * @author cfugu
 */
public interface ITransaccionesRepository extends JpaRepository<Transacciones, Long> {

    public Optional<Transacciones> findById(Long id);

    public Optional<List<Transacciones>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate);

    public Optional<List<Transacciones>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate);

    public Optional<List<Transacciones>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate);

    public Optional<List<Transacciones>> findByFechaRegistroBetween(Date startDate, Date endDate);

}
