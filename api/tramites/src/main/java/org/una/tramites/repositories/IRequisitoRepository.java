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
import org.una.tramites.entities.Requisito;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author colo7
 */
public interface IRequisitoRepository extends JpaRepository<Requisito, Long>{
    @Query("select u from Requisito u where u.estado=estado")
    public List<Requisito> findByEstadoContaining(@Param("estado")boolean estado);
    
    //por fecha registro
    
}
