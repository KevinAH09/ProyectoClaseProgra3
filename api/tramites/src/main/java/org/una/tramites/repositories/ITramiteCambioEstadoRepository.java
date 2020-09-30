/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.entities.TramiteRegistrado;

/**
 *
 * @author cfugu
 */
public interface ITramiteCambioEstadoRepository extends JpaRepository<TramiteCambioEstado, Long> {

    @Query("SELECT t FROM TramiteCambioEstado t LEFT JOIN t.tramiteRegistrado d WHERE d.cliente.cedula =:cedula")
    public List<TramiteCambioEstado> findByCedulaCliente (@Param("cedula") String cedula);
    
    
    @Query("SELECT t FROM TramiteCambioEstado t LEFT JOIN t.tramiteRegistrado d WHERE d.id=:id")
    public List<TramiteCambioEstado> findByTramiteRegistradId (@Param("id") Long id);
    
    @Query("SELECT t FROM TramiteCambioEstado t LEFT JOIN t.tramiteEstado d WHERE d.nombre=:nombre")
    public List<TramiteCambioEstado> findByEstado(@Param("nombre") String nombre);
    
    @Query("SELECT t FROM TramiteCambioEstado t LEFT JOIN t.tramiteEstado d WHERE d.nombre=:nombre")
    public List<TramiteCambioEstado> findByFechaRegistro(@Param("nombre") String nombre);
    
    
    
    
    
    
}
