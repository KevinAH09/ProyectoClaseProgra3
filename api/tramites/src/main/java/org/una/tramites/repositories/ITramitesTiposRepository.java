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
import org.una.tramites.entities.TramitesTipos;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author Bosco
 */
public interface ITramitesTiposRepository extends JpaRepository<TramitesTipos, Long>  {
    
    
    public List<TramitesTipos> findByEstadoContaining(@Param("estado") boolean estado);
    
    public List<TramitesTipos> findByDepartamentoId(Long id);

 
}
