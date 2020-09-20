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
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.entities.Variacion;
import org.una.tramites.repositories.IVariacionRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class VariacionServiceImplementation implements IVariacionService {

    @Autowired
    private IVariacionRepository variacionesRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findAll() {
        return (Optional<List<VariacionDTO>>) ConversionLista.findList(Optional.ofNullable(variacionesRepository.findAll()), VariacionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VariacionDTO> findById(Long id) {
        return (Optional<VariacionDTO>) ConversionLista.oneToDto(Optional.ofNullable(variacionesRepository.findById(id)), VariacionDTO.class);
    }

    @Override
    public Optional<List<VariacionDTO>> findByEstadoContaining(boolean estado) {
        return (Optional<List<VariacionDTO>>) ConversionLista.findList(Optional.ofNullable(variacionesRepository.findByEstadoContaining(estado)), VariacionDTO.class);
    }

    @Override
    @Transactional
    public VariacionDTO create(VariacionDTO usuario) {
        Variacion var = MapperUtils.EntityFromDto(usuario, Variacion.class);
        return MapperUtils.DtoFromEntity(variacionesRepository.save(var), VariacionDTO.class);
    }

    @Override
    @Transactional
    public Optional<VariacionDTO> update(VariacionDTO var, Long id) {
        if (variacionesRepository.findById(id).isPresent()) {
            Variacion vari = MapperUtils.EntityFromDto(var, Variacion.class);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(variacionesRepository.save(vari), VariacionDTO.class));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {

        variacionesRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        variacionesRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VariacionDTO>> findByTramiteTipoId(Long id) {
        return (Optional<List<VariacionDTO>>) ConversionLista.findList(Optional.ofNullable(variacionesRepository.findByTramiteTipoId(id)), VariacionDTO.class);

    }

}
