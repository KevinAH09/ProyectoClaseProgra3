/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.utils;

import java.util.List;
import java.util.Optional;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.entities.Usuario;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
public class ConversionLista {
    public static Optional<List<UsuarioDTO>> findList(List<Usuario> list) {
        if (list != null) {
            List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, UsuarioDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<List<UsuarioDTO>> findList(Optional<List<Usuario>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return Optional.empty();
        }
    }
    
    public static Optional<UsuarioDTO> oneToDto(Optional<Usuario> one) {
        if (one.isPresent()) {
            UsuarioDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), UsuarioDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return Optional.empty();
        }
    }
}
