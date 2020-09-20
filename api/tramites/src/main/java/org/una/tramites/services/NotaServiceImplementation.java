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
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;
import org.una.tramites.repositories.INotaRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Service
public class NotaServiceImplementation implements INotaService {

    @Autowired
    private INotaRepository notaRepository;
    
    public static Optional<List<NotaDTO>> findList(List<Nota> list) {
        if (list != null) {
            List<NotaDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, NotaDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<List<NotaDTO>> findList(Optional<List<Nota>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return Optional.empty();
        }
    }
    
    public static Optional<NotaDTO> oneToDto(Optional<Nota> one) {
        if (one.isPresent()) {
            NotaDTO PermisoDTO = MapperUtils.DtoFromEntity(one.get(), NotaDTO.class);
            return Optional.ofNullable(PermisoDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<NotaDTO>> findAll() {
        return findList((notaRepository.findAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotaDTO> findById(Long id) {
        return oneToDto(notaRepository.findById(id));
    }

    @Override
    @Transactional
    public NotaDTO create(NotaDTO variacion) {
       Nota user = MapperUtils.EntityFromDto(variacion, Nota.class);
        user = notaRepository.save(user);
        return MapperUtils.DtoFromEntity(user, NotaDTO.class);
    }

    @Override
    @Transactional
    public Optional<NotaDTO> update(NotaDTO notas, Long id) {
         if (notaRepository.findById(id).isPresent()) {
            Nota user = MapperUtils.EntityFromDto(notas, Nota.class);
            user = notaRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, NotaDTO.class));
        } else {
            return null;
        }
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
    public Optional<NotaDTO> findByTitulo(String titulo) {
        return oneToDto(Optional.ofNullable(notaRepository.findByTitulo(titulo)));
    }

  

}
