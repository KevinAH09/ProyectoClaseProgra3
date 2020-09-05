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
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.repositories.ITramiteEstadoRepository;

/**
 *
 * @author cordo
 */
@Service
public class TramiteEstadoServiceImplementation implements ITramiteEstadoService {

    @Autowired
    private ITramiteEstadoRepository tramiteEstadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteEstado> findById(Long id) {
        return tramiteEstadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteEstado>> findAll() {
        return Optional.ofNullable(tramiteEstadoRepository.findAll());
    }

    @Override
    @Transactional
    public TramiteEstado create(TramiteEstado tramites) {
        return tramiteEstadoRepository.save(tramites);
    }

    @Override
    public Optional<TramiteEstado> update(TramiteEstado tramites, Long id) {
        if (tramiteEstadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tramiteEstadoRepository.save(tramites));
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
    public Optional<TramiteEstado> findByNombre(String nombre) {
        return Optional.ofNullable(tramiteEstadoRepository.findByNombre(nombre));
    }
    
}
