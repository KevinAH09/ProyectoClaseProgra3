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
    private IPermisoRepository permisosOtorgadosRepository;

    public static Optional<List<PermisoDTO>> findList(List<Permiso> list) {
        if (list != null) {
            List<PermisoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, PermisoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }

    public static Optional<List<PermisoDTO>> findList(Optional<List<Permiso>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }
    
    public static Optional<PermisoDTO> oneToDto(Optional<Permiso> one) {
        if (one.isPresent()) {
            PermisoDTO PermisoDTO = MapperUtils.DtoFromEntity(one.get(), PermisoDTO.class);
            return Optional.ofNullable(PermisoDTO);
        } else {
            return null;
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findAll() {
        return findList((permisosOtorgadosRepository.findAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findById(Long id) {
        return oneToDto(permisosOtorgadosRepository.findById(id));
    }

    @Override
    @Transactional
    public PermisoDTO create(PermisoDTO permiso) {
        Permiso user = MapperUtils.EntityFromDto(permiso, Permiso.class);
        user = permisosOtorgadosRepository.save(user);
        return MapperUtils.DtoFromEntity(user, PermisoDTO.class);
    }

    @Override
    @Transactional
    public Optional<PermisoDTO> update(PermisoDTO permiso, Long id) {
       if (permisosOtorgadosRepository.findById(id).isPresent()) {
           Permiso user = MapperUtils.EntityFromDto(permiso, Permiso.class);
            user = permisosOtorgadosRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, PermisoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoDTO>> findByEstado(boolean estado) {
        return findList(Optional.ofNullable(permisosOtorgadosRepository.findByEstado(estado)));
    }

    @Override
    public Optional<List<PermisoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return findList(Optional.ofNullable(permisosOtorgadosRepository.findByFechaRegistroBetween(startDate, endDate)));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisosOtorgadosRepository.deleteById(id);
    }
    @Override
    @Transactional
    public void deleteAll() {
        permisosOtorgadosRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoDTO> findByCodigo(String codigo) {
         return oneToDto(Optional.ofNullable(permisosOtorgadosRepository.findByCodigo(codigo)));
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByEstado(boolean estado) {
        return permisosOtorgadosRepository.countByEstado(estado);
    }
}
