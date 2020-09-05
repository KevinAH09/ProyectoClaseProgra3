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
import org.una.tramites.entities.Nota;
import org.una.tramites.repositories.INotaRepository;

/**
 *
 * @author colo7
 */
@Service
public class NotaServiceImplementation implements INotaService {

    @Autowired
    private INotaRepository notaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Nota>> findAll() {
        return Optional.ofNullable(notaRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Nota> findById(Long id) {
        return notaRepository.findById(id);
    }

    @Override
    @Transactional
    public Nota create(Nota variacion) {
        return notaRepository.save(variacion);
    }

    @Override
    @Transactional
    public Optional<Nota> update(Nota notas, Long id) {
        if (notaRepository.findById(id).isPresent()) {
            return Optional.ofNullable(notaRepository.save(notas));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        notaRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Nota> findByTitulo(String titulo) {
        return Optional.ofNullable(notaRepository.findByTitulo(titulo));
    }

  

}
