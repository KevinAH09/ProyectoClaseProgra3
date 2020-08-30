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
import org.una.tramites.entities.Transaccion;

/**
 *
 * @author cfugu
 */
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {

//    @Query("SELECT u FROM Usuario u LEFT JOIN u.departamento d WHERE u.esJefe=1 AND d.id=:id")
    @Query("select u from Transaccion u JOIN u.permiso_Otorgado d where d.usuario = usuario and (u.fechaRegistro between fechaRegistro and fechaRegistro1)")
    public Optional<List<Transaccion>> findByUsuarioIdAndFechaRegistroBetween(@Param("usuario") Long usuario, @Param("fechaRegistro") Date startDate, @Param("fechaRegistro1") Date endDate);

    @Query("select u from Transaccion u JOIN u.permiso_Otorgado d where d.permisoId = permiso and (u.fechaRegistro between fechaRegistro and fechaRegistro1)")
    public Optional<List<Transaccion>> findByPermisoIdAndFechaRegistroBetween(@Param("permiso") Long permiso, @Param("fechaRegistro") Date startDate, @Param("fechaRegistro1") Date endDate);

    @Query("select d from Transaccion d where d.objeto= objeto and (d.fechaRegistro between fechaRegistro and fechaRegistro1)")
    public Optional<List<Transaccion>> findByObjetoAndFechaRegistroBetween(@Param("objeto") String objeto, @Param("fechaRegistro") Date startDate, @Param("fechaRegistro1") Date endDate);

    public Optional<List<Transaccion>> findByFechaRegistroBetween(@Param("fechaRegistro") Date startDate, @Param("fechaRegistro1") Date endDate);

    //public Optional<List<Transaccion>> findByPermisosOtorgados(Long id);
}
