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
import org.una.tramites.utils.ConversionLista;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@Service
public class ClienteServiceImplementation implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findAll() {
        return (Optional<List<ClienteDTO>>) ConversionLista.findList((clienteRepository.findAll()),ClienteDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClienteDTO> findById(Long id) {
       return (Optional<ClienteDTO>) ConversionLista.oneToDto(clienteRepository.findById(id),ClienteDTO.class);   
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByCedulaAproximate(String cedula) {
        return (Optional<List<ClienteDTO>>) ConversionLista.findList(clienteRepository.findByCedulaContaining(cedula),ClienteDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ClienteDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return  (Optional<List<ClienteDTO>>) ConversionLista.findList(clienteRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto),ClienteDTO.class);
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
        return (Optional<ClienteDTO>) ConversionLista.oneToDto(Optional.ofNullable(clienteRepository.findByCedula(cedula)),ClienteDTO.class);
    }

}
