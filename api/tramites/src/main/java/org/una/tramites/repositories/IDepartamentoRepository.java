/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author colo7
 */
public interface IDepartamentoRepository extends JpaRepository<Departamento, Long> {

    public List<Departamento> findByEstadoContaining(@Param("estado") boolean estado);
    
    public List<Departamento> findByNombreContainingIgnoreCase(String nombre);

    @Query("select u from Departamento u where UPPER(u.nombre) like CONCAT('%',UPPER(:nombre),'%')")
    public Departamento findNombreWithLikeSQL(@Param("nombre") String nombre);

}
