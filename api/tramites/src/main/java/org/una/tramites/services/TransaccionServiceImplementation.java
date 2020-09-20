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
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.entities.Usuario;
import org.una.tramites.repositories.ITransaccionRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cfugu
 */
@Service
public class TransaccionServiceImplementation implements ITransaccionService {

    @Autowired
    private ITransaccionRepository transaccionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionDTO> findById(Long id) {
        return (Optional<TransaccionDTO>)ConversionLista.oneToDto(transaccionRepository.findById(id),TransaccionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByUsuarioIdAndFechaRegistroBetween(Long usuarioId, Date startDate, Date endDate) {
        return (Optional<List<TransaccionDTO>>) ConversionLista.findList((transaccionRepository.findByUsuarioIdAndFechaRegistroBetween(usuarioId, startDate, endDate)),TransaccionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByPermisoIdAndFechaRegistroBetween(Long permisoId, Date startDate, Date endDate) {
        return (Optional<List<TransaccionDTO>>) ConversionLista.findList((transaccionRepository.findByPermisoIdAndFechaRegistroBetween(permisoId, startDate, endDate)),TransaccionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByObjetoAndFechaRegistroBetween(String objeto, Date startDate, Date endDate) {
        return (Optional<List<TransaccionDTO>>) ConversionLista.findList((transaccionRepository.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate)),TransaccionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
       return (Optional<List<TransaccionDTO>>) ConversionLista.findList((transaccionRepository.findByFechaRegistroBetween(startDate, endDate)),TransaccionDTO.class);
    }

    @Override
    @Transactional
    public TransaccionDTO create(TransaccionDTO transaccion) {
        Transaccion tran = MapperUtils.EntityFromDto(transaccion, Transaccion.class);
        return MapperUtils.DtoFromEntity(transaccionRepository.save(tran),TransaccionDTO.class);
    }

}
