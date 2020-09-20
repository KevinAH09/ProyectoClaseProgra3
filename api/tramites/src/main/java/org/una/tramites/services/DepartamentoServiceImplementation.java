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
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Service
public class DepartamentoServiceImplementation implements IDepartamentoService {

    @Autowired
    private IDepartamentoRepository departamentoRepository;

    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findAll() {
        return (Optional<List<DepartamentoDTO>>) ConversionLista.findList((departamentoRepository.findAll()),DepartamentoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepartamentoDTO> findById(Long id) {
         return (Optional<DepartamentoDTO>)ConversionLista.oneToDto(departamentoRepository.findById(id),DepartamentoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<DepartamentoDTO>> findByNombreAproximateIgnoreCase(String nombre) {
        return (Optional<List<DepartamentoDTO>>) ConversionLista.findList(departamentoRepository.findByNombreContainingIgnoreCase(nombre),DepartamentoDTO.class);
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
        return (Optional<List<DepartamentoDTO>>) ConversionLista.findList(Optional.ofNullable(departamentoRepository.findByEstado(estado)), DepartamentoDTO.class);

    }

}
