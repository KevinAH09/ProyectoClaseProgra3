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
import org.una.tramites.entities.ParametroGeneral;
import org.una.tramites.repositories.IParametroGeneralRepository;

/**
 *
 * @author colo7
 */
@Service
public class ParametroGeneralServiceImplementation implements IParametroGeneralService{
    
    @Autowired
    private IParametroGeneralRepository parametroGeneralRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneral>> findByNombre(String nombre) {
        return Optional.ofNullable(parametroGeneralRepository.findByNombre(nombre));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneral>> findByValor(String valor) {
        return Optional.ofNullable(parametroGeneralRepository.findByValor(valor));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneral>> findByDescripcion(String descripcion) {
        return Optional.ofNullable(parametroGeneralRepository.findByDescripcion(descripcion));
    }

    @Override
    @Transactional
    public Optional<ParametroGeneral> update(ParametroGeneral parametroGeneral, Long id) {
        if(parametroGeneralRepository.findById(id).isPresent()){
            return Optional.ofNullable(parametroGeneralRepository.save(parametroGeneral));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametroGeneral>> findAll() {
        return Optional.ofNullable(parametroGeneralRepository.findAll());
    }

    @Override
    public Optional<ParametroGeneral> create(ParametroGeneral parametroGeneral) {
        return Optional.ofNullable(parametroGeneralRepository.save(parametroGeneral));
        
    }
}
