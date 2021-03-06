package org.una.tramites.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.entities.Transaccion;

/**
 *
 * @author colo7
 */
public interface IArchivoRelacionadoRepository extends JpaRepository<ArchivoRelacionado, Long>{
    
    @Query("SELECT a FROM ArchivoRelacionado a LEFT JOIN a.tramiteRegistrado t WHERE a.tramiteRegistrado.id =:id")
    public List<ArchivoRelacionado> findByTramiteRegistrado(@Param("id")Long id);
    
    public List<ArchivoRelacionado> findByFechaRegistro(Date fechaRegistro);
    
}
