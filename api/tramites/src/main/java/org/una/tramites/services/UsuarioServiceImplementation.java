/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.repositories.IUsuarioRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ConversionLista;

/**
 *
 * @author Bosco
 */
@Service
public class UsuarioServiceImplementation implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findAll() {
        return (Optional<List<UsuarioDTO>>) ConversionLista.findList((usuarioRepository.findAll()),UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findById(Long id) {
        return (Optional<UsuarioDTO>)ConversionLista.oneToDto(usuarioRepository.findById(id),UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByCedulaAproximate(String cedula) {
        return (Optional<List<UsuarioDTO>>) ConversionLista.findList(usuarioRepository.findByCedulaContaining(cedula),UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByNombreCompletoAproximateIgnoreCase(String nombreCompleto) {
        return (Optional<List<UsuarioDTO>>)ConversionLista.findList(usuarioRepository.findByNombreCompletoContainingIgnoreCase(nombreCompleto),UsuarioDTO.class);
    }

    @Override
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuario) {
        encriptarPassword(usuario);
        Usuario user = MapperUtils.EntityFromDto(usuario, Usuario.class);
        user = usuarioRepository.save(user);
        return MapperUtils.DtoFromEntity(user, UsuarioDTO.class);
    }

    @Override
    @Transactional
    public Optional<UsuarioDTO> update(UsuarioDTO usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            Usuario user = MapperUtils.EntityFromDto(usuario, Usuario.class);
            user = usuarioRepository.save(user);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(user, UsuarioDTO.class));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
//   
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//
//        Optional<Usuario> usuario = findByCedula(authenticationRequest.getCedula());
//
//        if (usuario.isPresent()) {
//            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
//            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuario.get(), UsuarioDTO.class);
//            authenticationResponse.setUsuario(usuarioDto);
//            List<PermisoOtorgadoDTO> permisosOtorgadosDto = MapperUtils.DtoListFromEntityList(usuario.get().getPermisoOtorgado(), PermisoOtorgadoDTO.class);
//            authenticationResponse.setPermisos(permisosOtorgadosDto);
//
//            return authenticationResponse;
//        } else {
//            return null;
//        }
//    }
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<Usuario> login(Usuario usuario) {
//        encriptarPassword(usuario);
//        return Optional.ofNullable(usuarioRepository.findByCedulaAndPasswordEncriptado(usuario.getPasswordEncriptado(), usuario.getCedula()));
//    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByDepartamentoId(Long id) {
        return (Optional<List<UsuarioDTO>>) ConversionLista.findList(usuarioRepository.findByDepartamentoId(id),UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findJefeByDepartamento(Long id) {
        Optional<Usuario> result = usuarioRepository.findJefeByDepartamento(id);
        if (result != null) {
            UsuarioDTO usuarioDTO = MapperUtils.DtoFromEntity(result.get(), UsuarioDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByCedulaAndPassword(String cedula, String password) {
        //System.out.println("org.una.tramites.services.UsuarioServiceImplementation.findByCedulaAndPassword()" + password + "  " + cedula);
        return (Optional<UsuarioDTO>)ConversionLista.oneToDto(Optional.ofNullable(usuarioRepository.findByCedulaAndPasswordEncriptado(cedula, bCryptPasswordEncoder.encode(password))),UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuarioDTO>> findByEstadoContaining(boolean estado) {
        return (Optional<List<UsuarioDTO>>)ConversionLista.findList(Optional.ofNullable(usuarioRepository.findByEstadoContaining(estado)),UsuarioDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> findByCedula(String cedula) {
        return (Optional<UsuarioDTO>)ConversionLista.oneToDto(Optional.ofNullable(usuarioRepository.findByCedula(cedula)),UsuarioDTO.class);
    }

    private UsuarioDTO encriptarPassword(UsuarioDTO usuario) {
        String password = usuario.getPasswordEncriptado();
        if (!password.isBlank()) {
            usuario.setPasswordEncriptado(bCryptPasswordEncoder.encode(password));
        }
        return usuario;
    }

}
