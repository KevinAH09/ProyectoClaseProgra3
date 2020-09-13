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
import org.una.tramites.entities.Variacion;

/**
 *
 * @author Bosco
 */
public interface IVariacionRepository  extends JpaRepository<Variacion, Long>{
    
    @Query("select u from Variacion u where u.estado=estado")
    public List<Variacion> findByEstadoContaining(boolean estado);
    
    public List<Variacion> findByTramiteTipoId(Long id);
}
