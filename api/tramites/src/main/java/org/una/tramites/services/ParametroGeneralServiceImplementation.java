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
import org.una.tramites.dto.ParametroGeneralDTO;
import org.una.tramites.entities.ParametroGeneral;
import org.una.tramites.repositories.IParametroGeneralRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Service
public class ParametroGeneralServiceImplementation implements IParametroGeneralService{
    
    @Autowired
    private IParametroGeneralRepository parametroGeneralRepository;
    
    public static Optional<List<ParametroGeneralDTO>> findList(List<ParametroGeneral> list) {
        if (list != null) {
            List<ParametroGeneralDTO> ParametroGeneralDTO = MapperUtils.DtoListFromEntityList(list, ParametroGeneralDTO.class);
            return Optional.ofNullable(ParametroGeneralDTO);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<List<ParametroGeneralDTO>> findList(Optional<List<ParametroGeneral>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return Optional.empty();
        }
    }
    
    public static Optional<ParametroGeneralDTO> oneToDto(Optional<ParametroGeneral> one) {
        if (one.isPresent()) {
            ParametroGeneralDTO ParametroGeneralDTO = MapperUtils.DtoFromEntity(one.get(), ParametroGeneralDTO.class);
            return Optional.ofNullable(ParametroGeneralDTO);
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findByNombre(String nombre) {
        return findList(parametroGeneralRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findByValor(String valor) {
        return findList(parametroGeneralRepository.findByValor(valor));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findByDescripcion(String descripcion) {
        return findList(parametroGeneralRepository.findByDescripcion(descripcion));
    }

    @Override
    @Transactional
    public Optional<ParametroGeneralDTO> update(ParametroGeneralDTO parametroGeneral, Long id) {
        if (parametroGeneralRepository.findById(id).isPresent()) {
            ParametroGeneral user = MapperUtils.EntityFromDto(parametroGeneral, ParametroGeneral.class);
            user = parametroGeneralRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, ParametroGeneralDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findAll() {
       return findList((parametroGeneralRepository.findAll()));
    }

    @Override
    public ParametroGeneralDTO create(ParametroGeneralDTO parametroGeneral) {
        ParametroGeneral user = MapperUtils.EntityFromDto(parametroGeneral, ParametroGeneral.class);
        user = parametroGeneralRepository.save(user);
        return MapperUtils.DtoFromEntity(user, ParametroGeneralDTO.class);
        
    }

    @Override
    public Optional<List<ParametroGeneralDTO>> findByEstado(boolean estado) {
        return findList(Optional.ofNullable(parametroGeneralRepository.findByEstado(estado)));
    }

    @Override
    public Optional<ParametroGeneralDTO> findById(Long id) {
      return oneToDto(parametroGeneralRepository.findById(id));
    }
    
}
