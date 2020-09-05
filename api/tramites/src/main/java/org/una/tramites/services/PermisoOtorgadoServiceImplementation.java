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
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.repositories.IPermisoOtorgadoRepository;


/**
 *
 * @author cfugu
 */
@Service
public class PermisoOtorgadoServiceImplementation implements IPermisoOtorgadoService{

    @Autowired
    private IPermisoOtorgadoRepository permisoOtorgadosRepos;

    

    @Override
    @Transactional(readOnly = true)
    public Optional findByPermisoIdAndEstado(Long permisoId, boolean estado) {
       return Optional.ofNullable(permisoOtorgadosRepos.findByPermisoIdAndEstado(permisoId, estado));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional findByFechaRegistroBetween(Date startDate, Date endDate) {
        return Optional.ofNullable(permisoOtorgadosRepos.findByFechaRegistroBetween(startDate, endDate));
    }

    @Override
    @Transactional
    public PermisoOtorgado create(PermisoOtorgado permisoOtorgado) {
        return permisoOtorgadosRepos.save(permisoOtorgado);
    }

    @Override
    @Transactional
    public Optional<PermisoOtorgado> update(PermisoOtorgado permisoOtorgado, Long id) {
        if (permisoOtorgadosRepos.findById(id).isPresent()) {
            return Optional.ofNullable(permisoOtorgadosRepos.save(permisoOtorgado));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        permisoOtorgadosRepos.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        permisoOtorgadosRepos.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PermisoOtorgado> findById(Long id) {
        return permisoOtorgadosRepos.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgado>> findByUsuarioId(Long usuarioId) {
        return permisoOtorgadosRepos.findByUsuarioId(usuarioId); 
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgado>> findByPermisoId(Long permisoId) {
        return permisoOtorgadosRepos.findByPermisoId(permisoId);  
    }

    @Override
    @Transactional(readOnly = true)
    public Optional findByUsuarioIdAndEstado(Long usuarioId, boolean estado) {
        return Optional.ofNullable(permisoOtorgadosRepos.findByUsuarioIdAndEstado(usuarioId, estado));
    }

   
    
}
