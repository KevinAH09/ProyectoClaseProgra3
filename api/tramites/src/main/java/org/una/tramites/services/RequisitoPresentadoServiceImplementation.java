
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.repositories.IRequisitoPresentadoRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

@Service
public class RequisitoPresentadoServiceImplementation implements IRequisitoPresentadoService{
    
    @Autowired
    private IRequisitoPresentadoRepository requisitoPresentadoRepository;

     public static Optional<List<RequisitoPresentadoDTO>> findList(List<RequisitoPresentado> list) {
        if (list != null) {
            List<RequisitoPresentadoDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, RequisitoPresentadoDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<List<RequisitoPresentadoDTO>> findList(Optional<List<RequisitoPresentado>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return Optional.empty();
        }
    }
    
    public static Optional<RequisitoPresentadoDTO> oneToDto(Optional<RequisitoPresentado> one) {
        if (one.isPresent()) {
            RequisitoPresentadoDTO PermisoDTO = MapperUtils.DtoFromEntity(one.get(), RequisitoPresentadoDTO.class);
            return Optional.ofNullable(PermisoDTO);
        } else {
            return Optional.empty();
        }
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Optional<RequisitoPresentadoDTO> findById(Long id) {
        return (Optional<RequisitoPresentadoDTO>)ConversionLista.oneToDto(requisitoPresentadoRepository.findById(id),RequisitoPresentadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RequisitoPresentadoDTO>> findAll() {
        return (Optional<List<RequisitoPresentadoDTO>>) ConversionLista.findList((requisitoPresentadoRepository.findAll()),RequisitoPresentadoDTO.class);
    }

    
    @Override
    @Transactional
    public RequisitoPresentadoDTO create(RequisitoPresentadoDTO requisitoPresentado) {
        RequisitoPresentado user = MapperUtils.EntityFromDto(requisitoPresentado, RequisitoPresentado.class);
        user = requisitoPresentadoRepository.save(user);
        return MapperUtils.DtoFromEntity(user, RequisitoPresentadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<RequisitoPresentadoDTO> update(RequisitoPresentadoDTO requisitoPresentado, Long id) {
        if (requisitoPresentadoRepository.findById(id).isPresent()) {
            RequisitoPresentado user = MapperUtils.EntityFromDto(requisitoPresentado, RequisitoPresentado.class);
            user = requisitoPresentadoRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, RequisitoPresentadoDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        requisitoPresentadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        requisitoPresentadoRepository.deleteAll();
    }
    
}
