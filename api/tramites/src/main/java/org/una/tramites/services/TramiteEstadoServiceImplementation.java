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
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.repositories.ITramiteEstadoRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class TramiteEstadoServiceImplementation implements ITramiteEstadoService {

    @Autowired
    private ITramiteEstadoRepository tramiteEstadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteEstadoDTO> findById(Long id) {
        return (Optional<TramiteEstadoDTO>)ConversionLista.oneToDto(tramiteEstadoRepository.findById(id),TramiteEstadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteEstadoDTO>> findAll() {
        return (Optional<List<TramiteEstadoDTO>>) ConversionLista.findList((tramiteEstadoRepository.findAll()),TramiteEstadoDTO.class);
    }

    @Override
    @Transactional
    public TramiteEstadoDTO create(TramiteEstadoDTO tramites) {
        TramiteEstado user = MapperUtils.EntityFromDto(tramites, TramiteEstado.class);
        user = tramiteEstadoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, TramiteEstadoDTO.class);
    }

    @Override
    public Optional<TramiteEstadoDTO> update(TramiteEstadoDTO tramites, Long id) {
        if (tramiteEstadoRepository.findById(id).isPresent()) {
            TramiteEstado user = MapperUtils.EntityFromDto(tramites, TramiteEstado.class);
            user = tramiteEstadoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, TramiteEstadoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        tramiteEstadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tramiteEstadoRepository.deleteAll();
    }

    @Override
    public Optional<TramiteEstadoDTO> findByNombre(String nombre) {
        return (Optional<TramiteEstadoDTO>)ConversionLista.oneToDto(Optional.ofNullable(tramiteEstadoRepository.findByNombre(nombre)),TramiteEstadoDTO.class);
    }
    
}
