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
import org.una.tramites.entities.Variaciones;
import org.una.tramites.repositories.IVariacionesRepository;

/**
 *
 * @author Bosco
 */
@Service
public class VariacionesServiceImplementation implements IVariacionesService{

    @Autowired
    private IVariacionesRepository variacionesRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Variaciones>> findAll() {
        return Optional.ofNullable(variacionesRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Variaciones> findById(Long id) {
        return variacionesRepository.findById(id);
    }

    @Override
    public Optional<List<Variaciones>> findByEstadoContaining(boolean estado) {
        return Optional.ofNullable(variacionesRepository.findByEstadoContaining(estado));
    }

    @Override
    @Transactional
    public Variaciones create(Variaciones usuario) {
        return variacionesRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Variaciones> update(Variaciones usuario, Long id) {
        if (variacionesRepository.findById(id).isPresent()) {
            return Optional.ofNullable(variacionesRepository.save(usuario));
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
    public Optional findByTramiteTipoId(Long id) {
        return Optional.ofNullable(variacionesRepository.findByTramiteTipoId(id));

    }
    
}
