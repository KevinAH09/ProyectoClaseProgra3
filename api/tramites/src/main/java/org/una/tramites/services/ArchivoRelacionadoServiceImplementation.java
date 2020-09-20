
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.ArchivoRelacionadoDTO;
import org.una.tramites.entities.ArchivoRelacionado;
import org.una.tramites.repositories.IArchivoRelacionadoRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Service
public class ArchivoRelacionadoServiceImplementation implements IArchivoRelacionadoService{
    
    @Autowired
    private IArchivoRelacionadoRepository archivoRelacionadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ArchivoRelacionadoDTO> findById(Long id) {
        return (Optional<ArchivoRelacionadoDTO>)ConversionLista.oneToDto(archivoRelacionadoRepository.findById(id),ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findAll() {
       return (Optional<List<ArchivoRelacionadoDTO>>) ConversionLista.findList((archivoRelacionadoRepository.findAll()),ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByTramiteRegistrado(Long id) {
         return (Optional<List<ArchivoRelacionadoDTO>>) ConversionLista.findList(archivoRelacionadoRepository.findByTramiteRegistrado(id),ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistro(Date fechaRegistro) {
        return (Optional<List<ArchivoRelacionadoDTO>>)ConversionLista.findList(archivoRelacionadoRepository.findByFechaRegistro(fechaRegistro),ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional
    public ArchivoRelacionadoDTO create(ArchivoRelacionadoDTO archivoRelacionado) {
        ArchivoRelacionado ArchivoRelacionado = MapperUtils.EntityFromDto(archivoRelacionado, ArchivoRelacionado.class);
        ArchivoRelacionado = archivoRelacionadoRepository.save(ArchivoRelacionado);
        return MapperUtils.DtoFromEntity(ArchivoRelacionado, ArchivoRelacionadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<ArchivoRelacionadoDTO> update(ArchivoRelacionadoDTO ArchivoRelacionado, Long id) {
         if (archivoRelacionadoRepository.findById(id).isPresent()) {
            ArchivoRelacionado archivoRelacionado = MapperUtils.EntityFromDto(ArchivoRelacionado, ArchivoRelacionado.class);
            archivoRelacionado = archivoRelacionadoRepository.save(archivoRelacionado);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(archivoRelacionado, ArchivoRelacionadoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        archivoRelacionadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        archivoRelacionadoRepository.deleteAll();
    }
    
}
