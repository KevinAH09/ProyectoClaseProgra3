package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;

/**
 *
 * @author colo7
 */
public interface IRequisitoPresentadoService {
    
    public Optional<RequisitoPresentadoDTO> findById(Long id);
    
    public Optional<List<RequisitoPresentadoDTO>> findAll();
    
    public RequisitoPresentadoDTO create(RequisitoPresentadoDTO requisitoPresentado);
    
    public Optional<RequisitoPresentadoDTO> update(RequisitoPresentadoDTO requisitoPresentado, Long id);
    
    public void delete(Long id);
    
    public void deleteAll();
}
