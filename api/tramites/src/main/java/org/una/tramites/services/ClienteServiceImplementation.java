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
import org.una.tramites.dto.ClienteDTO;
import org.una.tramites.entities.Cliente;
import org.una.tramites.repositories.IClienteRepository;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Service
public class ClienteServiceImplementation implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    public static Optional<List<ClienteDTO>> findList(List<Cliente> list) {
        if (list != null) {
            List<ClienteDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, ClienteDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<List<ClienteDTO>> findList(Optional<List<Cliente>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return Optional.empty();
        }
    }
    
    public static Optional<ClienteDTO> oneToDto(Optional<Cliente> one) {
        if (one.isPresent()) {
            ClienteDTO PermisoDTO = MapperUtils.DtoFromEntity(one.get(), ClienteDTO.class);
            return Optional.ofNullable(PermisoDTO);
        } else {
            return Optional.empty();
        }
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findAll() {
        return findList((clienteRepository.findAll()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> findById(Long id) {
       return oneToDto(clienteRepository.findById(id));   
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByCedulaAproximate(String cedula) {
        return findList(clienteRepository.findByCedulaContaining(cedula));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return  findList((clienteRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto)));
    }

    @Override
    @Transactional
    public ClienteDTO create(ClienteDTO cliente) {
        Cliente user = MapperUtils.EntityFromDto(cliente, Cliente.class);
        user = clienteRepository.save(user);
        return MapperUtils.DtoFromEntity(user, ClienteDTO.class);
    }

    @Override
    @Transactional
    public Optional<ClienteDTO> update(ClienteDTO cliente, Long id) {
       if (clienteRepository.findById(id).isPresent()) {
            Cliente user = MapperUtils.EntityFromDto(cliente, Cliente.class);
            user = clienteRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, ClienteDTO.class));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        clienteRepository.deleteAll();
    }

    @Override
    public Optional<ClienteDTO> findByCedula(String cedula) {
        return oneToDto(Optional.ofNullable(clienteRepository.findByCedula(cedula)));
    }

}
