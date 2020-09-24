/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.entities.Usuario;

/**
 *
 * @author colo7
 */
public interface ITramiteRegistradoRepository extends JpaRepository<TramiteRegistrado, Long>{
    
    @Query("SELECT t FROM TramiteRegistrado t LEFT JOIN t.cliente d WHERE t.cliente.id =:id")
    public List<TramiteRegistrado> findByClientesId(Long id);
    
//    public List<TramiteRegistrado> findByTramitesTiposId(Long id);
}
