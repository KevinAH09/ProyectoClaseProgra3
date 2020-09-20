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
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Service
public class ParametroGeneralServiceImplementation implements IParametroGeneralService{
    
    @Autowired
    private IParametroGeneralRepository parametroGeneralRepository;
    
    
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findByNombre(String nombre) {
        return (Optional<List<ParametroGeneralDTO>>) ConversionLista.findList(parametroGeneralRepository.findByNombreContainingIgnoreCase(nombre),ParametroGeneralDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findByValor(String valor) {
       return (Optional<List<ParametroGeneralDTO>>) ConversionLista.findList(parametroGeneralRepository.findByValor(valor),ParametroGeneralDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneralDTO>> findByDescripcion(String descripcion) {
        return (Optional<List<ParametroGeneralDTO>>) ConversionLista.findList(parametroGeneralRepository.findByDescripcion(descripcion),ParametroGeneralDTO.class);
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
       return (Optional<List<ParametroGeneralDTO>>) ConversionLista.findList((parametroGeneralRepository.findAll()),ParametroGeneralDTO.class);
    }

    @Override
    public ParametroGeneralDTO create(ParametroGeneralDTO parametroGeneral) {
        ParametroGeneral user = MapperUtils.EntityFromDto(parametroGeneral, ParametroGeneral.class);
        user = parametroGeneralRepository.save(user);
        return MapperUtils.DtoFromEntity(user, ParametroGeneralDTO.class);
        
    }

    @Override
    public Optional<List<ParametroGeneralDTO>> findByEstado(boolean estado) {
        return (Optional<List<ParametroGeneralDTO>>)ConversionLista.findList(Optional.ofNullable(parametroGeneralRepository.findByEstado(estado)),ParametroGeneralDTO.class);
    }

    @Override
    public Optional<ParametroGeneralDTO> findById(Long id) {
      return (Optional<ParametroGeneralDTO>)ConversionLista.oneToDto(parametroGeneralRepository.findById(id),ParametroGeneralDTO.class);
    }
    
}
