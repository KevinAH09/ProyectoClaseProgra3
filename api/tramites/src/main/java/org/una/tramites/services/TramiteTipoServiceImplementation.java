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
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.repositories.ITramiteTipoRepository;

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
    public Optional<List<TramiteTipo>> findAll() {
       return Optional.ofNullable(tramitesTiposRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteTipo> findById(Long id) {
        return tramitesTiposRepository.findById(id);
    }

    @Override
    @Transactional
    public TramiteTipo create(TramiteTipo usuario) {
        return tramitesTiposRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipo>> findByEstadoContaining(boolean estado) {
         return Optional.ofNullable(tramitesTiposRepository.findByEstadoContaining(estado));
    }

    @Override
    @Transactional
    public Optional<TramiteTipo> update(TramiteTipo usuario, Long id) {
        if (tramitesTiposRepository.findById(id).isPresent()) {
            return Optional.ofNullable(tramitesTiposRepository.save(usuario));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteTipo>> findByDepartamentoId(Long id) {
        return Optional.ofNullable(tramitesTiposRepository.findByDepartamentoId(id));
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
