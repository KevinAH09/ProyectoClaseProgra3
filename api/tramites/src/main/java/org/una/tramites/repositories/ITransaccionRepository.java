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
import org.una.tramites.entities.Transaccion;

/**
 *
 * @author cfugu
 */
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {

    //preguntar al profe porque da error
//    public Optional<List<Transaccion>> findByUsuarioIdAndFechaRegistroBetween(@Param("permiso_Otorgado")Long usuario,@Param("fechaRegistro") Date startDate,@Param("fechaRegistro") Date endDate);
//
//    public Optional<List<Transaccion>> findByPermisoIdAndFechaRegistroBetween(@Param("permiso_Otorgado")Long permiso,@Param("fechaRegistro") Date startDate,@Param("fechaRegistro") Date endDate);

    public Optional<List<Transaccion>> findByObjetoAndFechaRegistroBetween(@Param("objeto")String objeto,@Param("fechaRegistro") Date startDate,@Param("fechaRegistro") Date endDate);

    public Optional<List<Transaccion>> findByFechaRegistroBetween(@Param("fechaRegistro")Date startDate,@Param("fechaRegistro") Date endDate);

    //public Optional<List<Transaccion>> findByPermisosOtorgados(Long id);
}
