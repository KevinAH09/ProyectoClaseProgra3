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
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

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
    public Optional<List<NotaDTO>> findAll() {
         return (Optional<List<NotaDTO>>) ConversionLista.findList((notaRepository.findAll()),NotaDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NotaDTO> findById(Long id) {
        return (Optional<NotaDTO>)ConversionLista.oneToDto(notaRepository.findById(id),NotaDTO.class);
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
        return (Optional<NotaDTO>)ConversionLista.oneToDto(Optional.ofNullable(notaRepository.findByTitulo(titulo)),NotaDTO.class);
    }

  

}
