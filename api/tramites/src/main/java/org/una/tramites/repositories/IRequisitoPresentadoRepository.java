
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.RequisitoPresentado;

/**
 *
 * @author colo7
 */
public interface IRequisitoPresentadoRepository extends JpaRepository<RequisitoPresentado, Long>{
    
    public List<RequisitoPresentado>findByRequisito(Long id);
    
    
}
