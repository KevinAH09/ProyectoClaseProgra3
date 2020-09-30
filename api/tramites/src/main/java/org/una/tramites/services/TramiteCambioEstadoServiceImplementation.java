/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import javax.xml.transform.Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.entities.TramiteCambioEstado;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.repositories.ITramiteCambioEstadoRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cfugu
 */
@Service
public class TramiteCambioEstadoServiceImplementation implements ITramiteCambioEstadoService {

    @Autowired
    private ITramiteCambioEstadoRepository tramiteCambioEstadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteCambioEstadoDTO>> findAll() {
        return (Optional<List<TramiteCambioEstadoDTO>>) ConversionLista.findList((tramiteCambioEstadoRepository.findAll()), TramiteCambioEstadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteCambioEstadoDTO> findById(Long id) {
        return (Optional<TramiteCambioEstadoDTO>) ConversionLista.oneToDto((tramiteCambioEstadoRepository.findById(id)), TramiteCambioEstadoDTO.class);
    }

    @Override
    @Transactional
    public TramiteCambioEstadoDTO create(TramiteCambioEstadoDTO tramiteRegistrado) {
        TramiteCambioEstado user = MapperUtils.EntityFromDto(tramiteRegistrado, TramiteCambioEstado.class);
        user = tramiteCambioEstadoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, TramiteCambioEstadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<TramiteCambioEstadoDTO> update(TramiteCambioEstadoDTO tramiteRegistrado, Long id) {
        if (tramiteCambioEstadoRepository.findById(id).isPresent()) {
            TramiteCambioEstado user = MapperUtils.EntityFromDto(tramiteRegistrado, TramiteCambioEstado.class);
            user = tramiteCambioEstadoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, TramiteCambioEstadoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        tramiteCambioEstadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tramiteCambioEstadoRepository.deleteAll();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Optional<TramiteCambioEstadoDTO> findByCedulaCliente(String cedula) {
//        Optional<List<TramiteCambioEstado>> result = tramiteCambioEstadoRepository.findByCedulaCliente(cedula);
//        if (result != null) {
//            TramiteCambioEstadoDTO usuarioDTO = MapperUtils.DtoFromEntity(result.get(), TramiteCambioEstadoDTO.class);
//            return Optional.ofNullable(usuarioDTO);
//        } else {
//            return null;
//        }
//    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteCambioEstadoDTO>> findByTramiteRegistradId(Long id) {
         return (Optional<List<TramiteCambioEstadoDTO>>) ConversionLista.findList((tramiteCambioEstadoRepository.findByTramiteRegistradId(id)), TramiteCambioEstadoDTO.class);
    }
}

    
