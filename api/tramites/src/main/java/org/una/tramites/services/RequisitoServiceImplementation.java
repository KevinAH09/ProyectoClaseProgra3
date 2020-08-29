/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.Requisito;
import org.una.tramites.repositories.IRequisitoRepository;

/**
 *
 * @author colo7
 */
public class RequisitoServiceImplementation implements IRequisitoService{

    
    @Autowired
    private IRequisitoRepository requisitoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Requisito>> findAll() {
       return Optional.ofNullable(requisitoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Requisito> findById(Long id) {
        return requisitoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Requisito>> findByEstadoContaining(boolean estado) {
          return Optional.ofNullable(requisitoRepository.findByEstadoContaining(estado));
    }

    @Override
    @Transactional
    public Requisito create(Requisito requisitos) {
        return requisitoRepository.save(requisitos);
    }

    @Override
    @Transactional
    public Optional<Requisito> update(Requisito requisitos, Long id) {
       if (requisitoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(requisitoRepository.save(requisitos));
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
