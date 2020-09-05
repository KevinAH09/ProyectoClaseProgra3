/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Variacion;

/**
 *
 * @author Bosco
 */
public interface IVariacionRepository  extends JpaRepository<Variacion, Long>{
    
    
    public List<Variacion> findByEstadoContaining(boolean estado);
    
    public Optional<List<Variacion>> findByTramiteTipoId(Long id);
}
