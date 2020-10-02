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
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.repositories.ITramiteRegistradoRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@Service
public class TramiteRegistradoServiceImplementation implements ITramiteRegistradoService{
    
    @Autowired
    private ITramiteRegistradoRepository tramiteRegistradoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteRegistradoDTO>> findAll() {
         return (Optional<List<TramiteRegistradoDTO>>) ConversionLista.findList((tramiteRegistradoRepository.findAll()),TramiteRegistradoDTO.class);
    }

    @Override
    @Transactional
    public TramiteRegistradoDTO create(TramiteRegistradoDTO tramiteRegistrado) {
        
         TramiteRegistrado tra = MapperUtils.EntityFromDto(tramiteRegistrado, TramiteRegistrado.class);
        return MapperUtils.DtoFromEntity(tramiteRegistradoRepository.save(tra), TramiteRegistradoDTO.class );
    }

    @Override
    @Transactional
    public Optional<TramiteRegistradoDTO> update(TramiteRegistradoDTO tramiteRegistrado, Long id) {
        if(tramiteRegistradoRepository.findById(id).isPresent()){
            TramiteRegistrado tra = MapperUtils.EntityFromDto(tramiteRegistrado, TramiteRegistrado.class);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(tramiteRegistradoRepository.save(tra),TramiteRegistradoDTO.class));
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tramiteRegistradoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        tramiteRegistradoRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TramiteRegistradoDTO> findById(Long id) {
       return (Optional<TramiteRegistradoDTO>) ConversionLista.oneToDto((tramiteRegistradoRepository.findById(id)),TramiteRegistradoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TramiteRegistradoDTO>> findByClientesCedula(String cedula) {
        return (Optional<List<TramiteRegistradoDTO>>) ConversionLista.findList((tramiteRegistradoRepository.findByClientesCedula(cedula)),TramiteRegistradoDTO.class);
    }
}
