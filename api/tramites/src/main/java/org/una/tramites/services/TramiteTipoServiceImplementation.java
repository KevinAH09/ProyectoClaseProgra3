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
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.dto.VariacionDTO;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.entities.Variacion;
import org.una.tramites.repositories.ITramiteTipoRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class TramiteTipoServiceImplementation implements ITramiteTipoService {
    
    @Autowired
    private ITramiteTipoRepository tramitesTiposRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> findAll() {
         return (Optional<List<TramiteTipoDTO>>) ConversionLista.findList((tramitesTiposRepository.findAll()),TramiteTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteTipoDTO> findById(Long id) {
        return (Optional<TramiteTipoDTO>)ConversionLista.oneToDto(tramitesTiposRepository.findById(id),VariacionDTO.class);
    }

    @Override
    @Transactional
    public TramiteTipoDTO create(TramiteTipoDTO trami) {
        TramiteTipo tra = MapperUtils.EntityFromDto(trami, TramiteTipo.class);
        return MapperUtils.DtoFromEntity(tramitesTiposRepository.save(tra), TramiteTipoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> findByEstado(boolean estado) {
        return (Optional<List<TramiteTipoDTO>>) ConversionLista.findList((tramitesTiposRepository.findByEstado(estado)),TramiteTipoDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramiteTipoDTO> update(TramiteTipoDTO trami, Long id) {
        if (tramitesTiposRepository.findById(id).isPresent()) {
            TramiteTipo tra = MapperUtils.EntityFromDto(trami, TramiteTipo.class);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(tramitesTiposRepository.save(tra), TramiteTipoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipoDTO>> findByDepartamentoId(Long id) {
        return (Optional<List<TramiteTipoDTO>>) ConversionLista.findList((tramitesTiposRepository.findByDepartamentoId(id)),TramiteTipoDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramitesTiposRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramitesTiposRepository.deleteAll();
    }

  
    
}
