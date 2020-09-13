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
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.repositories.ITramiteRegistradoRepository;

/**
 *
 * @author Bosco
 */
@Service
public class TramiteRegistradoServiceImplementation implements ITramiteRegistradoService{
    
    @Autowired
    private ITramiteRegistradoRepository tramiteRegistradoRepository;
    
    @Override
    public Optional<List<TramiteRegistrado>> findAll() {
         return Optional.ofNullable(tramiteRegistradoRepository.findAll());
    }

    @Override
    public TramiteRegistrado create(TramiteRegistrado tramiteRegistrado) {
        return tramiteRegistradoRepository.save(tramiteRegistrado);
    }

    @Override
    public Optional<TramiteRegistrado> update(TramiteRegistrado tramiteRegistrado, Long id) {
        if(tramiteRegistradoRepository.findById(id).isPresent())
            return Optional.ofNullable(tramiteRegistradoRepository.save(tramiteRegistrado));
        else
            return null;
    }

    @Override
    public void delete(Long id) {
        tramiteRegistradoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        tramiteRegistradoRepository.deleteAll();
    }

    @Override
    public Optional<TramiteRegistrado> findById(Long id) {
        return tramiteRegistradoRepository.findById(id);
    }
}
