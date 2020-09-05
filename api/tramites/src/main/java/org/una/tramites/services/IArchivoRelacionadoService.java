
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.ArchivoRelacionado;

/**
 *
 * @author colo7
 */
public interface IArchivoRelacionadoService {
    
    public Optional<ArchivoRelacionado> findById(Long id);
    
    public Optional<List<ArchivoRelacionado>> findAll();
    
    public Optional<List<ArchivoRelacionado>> findByTramiteRegistrado(Long id);
    
    public Optional<List<ArchivoRelacionado>> findByFechaRegistro(Date fechaRegistro);
    
    public ArchivoRelacionado create(ArchivoRelacionado archivoRelacionado);

    public Optional<ArchivoRelacionado> update(ArchivoRelacionado archivoRelacionado, Long id);

    public void delete(Long id);

    public void deleteAll();
}
