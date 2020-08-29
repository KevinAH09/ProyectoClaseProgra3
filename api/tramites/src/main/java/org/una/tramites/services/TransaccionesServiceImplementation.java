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
import org.una.tramites.entities.Transacciones;
import org.una.tramites.repositories.ITransaccionesRepository;

/**
 *
 * @author cfugu
 */
@Service
public class TransaccionesServiceImplementation implements ITransaccionesService {

    @Autowired
    private ITransaccionesRepository transaccionesRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Transacciones> findById(Long id) {
        return transaccionesRepository.findById(id);
    }

    @Override
    public Optional<List<Transacciones>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<List<Transacciones>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<List<Transacciones>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<List<Transacciones>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Transacciones create(Transacciones transaccion) {
        return transaccionesRepository.save(transaccion);
    }

}
