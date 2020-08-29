/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.tramites.entities.Permisos;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.repositories.IPermisosRepository;

/**
 *
 * @author cfugu
 */
@Service
public class PermisosServiceImplementation implements IPermisosService {

    @Autowired
    private IPermisosRepository permisosOtorgadosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permisos>> findAll() {
        return Optional.ofNullable(permisosOtorgadosRepository.findAll());
    }

    @Override
    public Optional<Permisos> findById(Long id) {
        return permisosOtorgadosRepository.findById(id);
    }

    @Override
    public Permisos create(Permisos permiso) {
        return permisosOtorgadosRepository.save(permiso);
    }

    @Override
    public Optional<Permisos> update(Permisos permiso, Long id) {
       if (permisosOtorgadosRepository.findById(id).isPresent()) {
            return Optional.ofNullable(permisosOtorgadosRepository.save(permiso));
        } else {
            return null;
        }
    }

    @Override
    public Optional<List<Permisos>> findByEstado(boolean estado) {
        return Optional.ofNullable(permisosOtorgadosRepository.findByEstadoContaining(estado));
    }

    @Override
    public Optional<List<Permisos>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
        permisosOtorgadosRepository.deleteById(id);
    }
    @Override
    public void deleteAll() {
        permisosOtorgadosRepository.deleteAll();
    }

}
