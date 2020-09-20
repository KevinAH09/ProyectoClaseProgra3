/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Service
public class DepartamentoServiceImplementation implements IDepartamentoService {

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    public static Optional<List<DepartamentoDTO>> findList(List<Departamento> list) {
        if (list != null) {
            List<DepartamentoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, DepartamentoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<List<DepartamentoDTO>> findList(Optional<List<Departamento>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return Optional.empty();
        }
    }
    
    public static Optional<DepartamentoDTO> oneToDto(Optional<Departamento> one) {
        if (one.isPresent()) {
            DepartamentoDTO PermisoDTO = MapperUtils.DtoFromEntity(one.get(), DepartamentoDTO.class);
            return Optional.ofNullable(PermisoDTO);
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findAll() {
        return findList((departamentoRepository.findAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentoDTO> findById(Long id) {
        return oneToDto(departamentoRepository.findById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findByNombreAproximateIgnoreCase(String nombre) {
        return findList(departamentoRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional
    public DepartamentoDTO create(DepartamentoDTO departamento) {
        Departamento user = MapperUtils.EntityFromDto(departamento, Departamento.class);
        user = departamentoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, DepartamentoDTO.class);
    }

    @Override
    @Transactional
    public Optional<DepartamentoDTO> update(DepartamentoDTO departamento, Long id) {
        if (departamentoRepository.findById(id).isPresent()) {
            Departamento user = MapperUtils.EntityFromDto(departamento, Departamento.class);
            user = departamentoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, DepartamentoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findByEstadoContaining(boolean estado) {
        return findList(Optional.ofNullable(departamentoRepository.findByEstado(estado)));

    }

}
