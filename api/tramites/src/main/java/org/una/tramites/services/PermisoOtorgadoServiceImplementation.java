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
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.entities.PermisoOtorgado;
import org.una.tramites.repositories.IPermisoOtorgadoRepository;
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cfugu
 */
@Service
public class PermisoOtorgadoServiceImplementation implements IPermisoOtorgadoService {

    @Autowired
    private IPermisoOtorgadoRepository permisoOtorgadosRepos;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByPermisoIdAndEstado(Long permisoId, boolean estado) {
        return (Optional<List<PermisoOtorgadoDTO>>) ConversionLista.findList(Optional.ofNullable(permisoOtorgadosRepos.findByPermisoIdAndEstado(permisoId, estado)), PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByFechaRegistroBetween(Date startDate, Date endDate) {
        return (Optional<List<PermisoOtorgadoDTO>>) ConversionLista.findList(Optional.ofNullable(permisoOtorgadosRepos.findByFechaRegistroBetween(startDate, endDate)), PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional
    public PermisoOtorgadoDTO create(PermisoOtorgadoDTO permisoOtorgado) {
        PermisoOtorgado user = MapperUtils.EntityFromDto(permisoOtorgado, PermisoOtorgado.class);
        user = permisoOtorgadosRepos.save(user);
        return MapperUtils.DtoFromEntity(user, PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional
    public Optional<PermisoOtorgadoDTO> update(PermisoOtorgadoDTO permisoOtorgado, Long id) {
        if (permisoOtorgadosRepos.findById(id).isPresent()) {
            PermisoOtorgado user = MapperUtils.EntityFromDto(permisoOtorgado, PermisoOtorgado.class);
            user = permisoOtorgadosRepos.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, PermisoOtorgadoDTO.class));
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
    public Optional<PermisoOtorgadoDTO> findById(Long id) {
        return (Optional<PermisoOtorgadoDTO>) ConversionLista.oneToDto(permisoOtorgadosRepos.findById(id), PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioId(Long usuarioId) {
        return (Optional<List<PermisoOtorgadoDTO>>) ConversionLista.findList(permisoOtorgadosRepos.findByUsuarioId(usuarioId), PermisoOtorgadoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByPermisoId(Long permisoId) {
        return (Optional<List<PermisoOtorgadoDTO>>) ConversionLista.findList(permisoOtorgadosRepos.findByPermisoId(permisoId), PermisoOtorgadoDTO.class);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PermisoOtorgadoDTO>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado) {
        return (Optional<List<PermisoOtorgadoDTO>>) ConversionLista.findList(Optional.ofNullable(permisoOtorgadosRepos.findByUsuarioIdAndEstado(usuarioId, estado)), PermisoOtorgadoDTO.class);
    }

}
