
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

    public static Optional<List<ArchivoRelacionadoDTO>> findList(List<ArchivoRelacionado> list) {
        if (list != null) {
            List<ArchivoRelacionadoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, ArchivoRelacionadoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<List<ArchivoRelacionadoDTO>> findList(Optional<List<ArchivoRelacionado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return Optional.empty();
        }
    }
    
    public static Optional<ArchivoRelacionadoDTO> oneToDto(Optional<ArchivoRelacionado> one) {
        if (one.isPresent()) {
            ArchivoRelacionadoDTO PermisoDTO = MapperUtils.DtoFromEntity(one.get(), ArchivoRelacionadoDTO.class);
            return Optional.ofNullable(PermisoDTO);
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<ArchivoRelacionadoDTO> findById(Long id) {
        return oneToDto(archivoRelacionadoRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findAll() {
       return findList((archivoRelacionadoRepository.findAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByTramiteRegistrado(Long id) {
         return findList(archivoRelacionadoRepository.findByTramiteRegistrado(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ArchivoRelacionadoDTO>> findByFechaRegistro(Date fechaRegistro) {
        return findList(archivoRelacionadoRepository.findByFechaRegistro(fechaRegistro));
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
