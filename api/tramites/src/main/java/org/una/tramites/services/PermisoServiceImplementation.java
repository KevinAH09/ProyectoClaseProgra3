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
import org.una.tramites.entities.Permiso;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.repositories.IPermisoRepository;

/**
 *
 * @author cfugu
 */
@Service
public class PermisoServiceImplementation implements IPermisoService {

    @Autowired
    private IPermisoRepository permisosOtorgadosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permiso>> findAll() {
        return Optional.ofNullable(permisosOtorgadosRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Permiso> findById(Long id) {
        return permisosOtorgadosRepository.findById(id);
    }

    @Override
    @Transactional
    public Permiso create(Permiso permiso) {
        return permisosOtorgadosRepository.save(permiso);
    }

    @Override
    @Transactional
    public Optional<Permiso> update(Permiso permiso, Long id) {
       if (permisosOtorgadosRepository.findById(id).isPresent()) {
            return Optional.ofNullable(permisosOtorgadosRepository.save(permiso));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Permiso>> findByEstado(boolean estado) {
        return Optional.ofNullable(permisosOtorgadosRepository.findByEstadoContaining(estado));
    }

    @Override
    public Optional findByFechaRegistroBetween(Date startDate, Date endDate) {
        return Optional.ofNullable(permisosOtorgadosRepository.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisosOtorgadosRepository.deleteById(id);
    }
    @Override
    @Transactional
    public void deleteAll() {
        permisosOtorgadosRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Permiso> findByCodigo(String codigo) {
         return Optional.ofNullable(permisosOtorgadosRepository.findByCodigo(codigo));
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByEstado(boolean estado) {
        return permisosOtorgadosRepository.countByEstado(estado);
    }

//    @Override
//    public long countByPermisos() {
//        return permisosOtorgadosRepository.countByPermisos();
//    }

   

}
