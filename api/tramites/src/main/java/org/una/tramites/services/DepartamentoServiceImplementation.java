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
import org.una.tramites.entities.Departamento;
import org.una.tramites.repositories.IDepartamentoRepository;

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
    public Optional<List<Departamento>> findAll() {
        return Optional.ofNullable(departamentoRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Departamento>> findByNombreAproximateIgnoreCase(String nombre) {
       return Optional.ofNullable(departamentoRepository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional
    public Departamento create(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    @Transactional
    public Optional<Departamento> update(Departamento departamento, Long id) {
        if (departamentoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(departamentoRepository.save(departamento));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Departamento>> findByEstadoContaining(boolean estado) {
        return Optional.ofNullable(departamentoRepository.findByEstadoContaining(estado));
    }

}
