/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.ParametroGeneral;

/**
 *
 * @author colo7
 */
public interface IParametroGeneralService {
   
    public Optional<List<ParametroGeneral>> findByEstado(boolean estado);
    
    public Optional<ParametroGeneral> findById(Long id);
    
    public Optional<List<ParametroGeneral>> findByNombre(String nombre);
    
    public Optional<List<ParametroGeneral>> findByValor(String valor);
    
    public Optional<List<ParametroGeneral>> findByDescripcion(String descripcion);
    
    public Optional<ParametroGeneral> update(ParametroGeneral parametroGeneral, Long id);
    
    public Optional<List<ParametroGeneral>> findAll();
    
    public Optional<ParametroGeneral> create(ParametroGeneral parametroGeneral);
}
