/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.ParametroGeneral;

/**
 *
 * @author colo7
 */
public interface IParametroGeneralRepository extends JpaRepository<ParametroGeneral, Long>{
    
    public List<ParametroGeneral> findByNombreContainingIgnoreCase(String nombre);
    
    public List<ParametroGeneral> findByValor(String valor);
    
    public List<ParametroGeneral> findByDescripcion(String descripcion);
    
    public List<ParametroGeneral> findByEstado(boolean estado);
}
