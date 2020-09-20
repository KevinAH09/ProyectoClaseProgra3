/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.tramites.entities.Permiso;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.repositories.IPermisoRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cfugu
 */
@Service
public class PermisoServiceImplementation implements IPermisoService {

    @Autowired
    private IPermisoRepository permisosRepository;

    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findAll() {
                return (Optional<List<PermisoDTO>>) ConversionLista.findList((permisosRepository.findAll()),PermisoDTO.class);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findById(Long id) {
        return (Optional<PermisoDTO>)ConversionLista.oneToDto(permisosRepository.findById(id),PermisoDTO.class);
    }

    @Override
    @Transactional
    public PermisoDTO create(PermisoDTO permiso) {
        Permiso user = MapperUtils.EntityFromDto(permiso, Permiso.class);
        user = permisosRepository.save(user);
        return MapperUtils.DtoFromEntity(user, PermisoDTO.class);
    }

    @Override
    @Transactional
    public Optional<PermisoDTO> update(PermisoDTO permiso, Long id) {
       if (permisosRepository.findById(id).isPresent()) {
           Permiso user = MapperUtils.EntityFromDto(permiso, Permiso.class);
            user = permisosRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, PermisoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findByEstado(boolean estado) {
        return (Optional<List<PermisoDTO>>)ConversionLista.findList(Optional.ofNullable(permisosRepository.findByEstadoContaining(estado)),PermisoDTO.class);
    }

    @Override
    public Optional<List<PermisoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return (Optional<List<PermisoDTO>>)ConversionLista.findList(Optional.ofNullable(permisosRepository.findByFechaRegistroBetween(startDate, endDate)),PermisoDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisosRepository.deleteById(id);
    }
    @Override
    @Transactional
    public void deleteAll() {
        permisosRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findByCodigo(String codigo) {
         return (Optional<PermisoDTO>)ConversionLista.oneToDto(Optional.ofNullable(permisosRepository.findByCodigo(codigo)),PermisoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByEstado(boolean estado) {
        return permisosRepository.countByEstado(estado);
    }
}
