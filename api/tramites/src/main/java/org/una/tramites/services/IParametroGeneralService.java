/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.ParametroGeneralDTO;
import org.una.tramites.entities.ParametroGeneral;

/**
 *
 * @author colo7
 */
public interface IParametroGeneralService {
   
    public Optional<List<ParametroGeneralDTO>> findByEstado(boolean estado);
    
    public Optional<ParametroGeneralDTO> findById(Long id);
    
    public Optional<List<ParametroGeneralDTO>> findByNombre(String nombre);
    
    public Optional<List<ParametroGeneralDTO>> findByValor(String valor);
    
    public Optional<List<ParametroGeneralDTO>> findByDescripcion(String descripcion);
    
    public Optional<ParametroGeneralDTO> update(ParametroGeneralDTO parametroGeneral, Long id);
    
    public Optional<List<ParametroGeneralDTO>> findAll();
    
    public ParametroGeneralDTO create(ParametroGeneralDTO parametroGeneral);
}
