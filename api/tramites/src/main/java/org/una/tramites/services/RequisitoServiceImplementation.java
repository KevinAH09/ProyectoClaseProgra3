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
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;
import org.una.tramites.repositories.IRequisitoRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Service
public class RequisitoServiceImplementation implements IRequisitoService{

    
    @Autowired
    private IRequisitoRepository requisitoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoDTO>> findAll() {
       return (Optional<List<RequisitoDTO>>) ConversionLista.findList((requisitoRepository.findAll()),RequisitoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoDTO> findById(Long id) {
        return (Optional<RequisitoDTO>)ConversionLista.oneToDto(requisitoRepository.findById(id),RequisitoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoDTO>> findByEstadoContaining(boolean estado) {
          return (Optional<List<RequisitoDTO>>)ConversionLista.findList(Optional.ofNullable(requisitoRepository.findByEstadoContaining(estado)),RequisitoDTO.class);
    }

    @Override
    @Transactional
    public RequisitoDTO create(RequisitoDTO requisitos) {
        Requisito user = MapperUtils.EntityFromDto(requisitos, Requisito.class);
        user = requisitoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, RequisitoDTO.class);
    }

    @Override
    @Transactional
    public Optional<RequisitoDTO> update(RequisitoDTO requisitos, Long id) {
       if (requisitoRepository.findById(id).isPresent()) {
            Requisito user = MapperUtils.EntityFromDto(requisitos, Requisito.class);
            user = requisitoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, RequisitoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
       requisitoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        requisitoRepository.deleteAll();
    }
    
}
