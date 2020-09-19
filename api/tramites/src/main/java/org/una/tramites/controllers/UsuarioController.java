/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.services.IUsuarioService;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/usuarios")
@Api(tags = {"Usuarios"})
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @PostMapping("/login")
//    @ResponseBody
//    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
//    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.BAD_REQUEST);
//        }
//        try {
//            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//            String token = service.login(authenticationRequest).getJwt();
//            if (!token.isBlank()) {
//                authenticationResponse.setJwt(token);
//                Optional<Usuario> user = service.findByCedula(authenticationRequest.getCedula());
//                UsuarioDTO userDto = MapperUtils.DtoFromEntity(user.get(), UsuarioDTO.class);
//                authenticationResponse.setUsuario(userDto);
//                authenticationResponse.setPermisos(userDto.getPermisos());
//                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
//            }
//        } catch(UsernameNotFoundException | BadCredentialsException ex){
//            return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.BAD_REQUEST);
//        }catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("/cedula/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(usuarioService.findByCedulaAproximate(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/cedula/{cedula}/password_encriptado/{password}")
    @ApiOperation(value = "Obtiene un Usuario por cedula y password", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByCedulaAndPassword(@PathVariable(value = "cedula") String cedula, @PathVariable(value = "password") String pass) {
        try {
            return new ResponseEntity<>(usuarioService.findByCedulaAndPassword(cedula, pass), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/cedula/{term}")
//    @ApiOperation(value = "Obtiene un usuario por su cedula", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
//    public ResponseEntity<?> findByCedula(@PathVariable(value = "term") String term) {
//        try {
//            Optional<List<Usuario>> result = usuarioService.findByCedula(term);
//            if (result.isPresent()) {
//                List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR','USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity(usuarioService.findByNombreCompletoAproximateIgnoreCase(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los usuarios por estado", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_INACTIVAR')")
    public ResponseEntity<?> findByEstadoContaining(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(usuarioService.findByEstadoContaining(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(usuarioService.create(usuarioDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un usuario", response = UsuarioDTO.class, tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<UsuarioDTO> usuarioUpdated = usuarioService.update(usuarioDTO, id);
                if (usuarioUpdated.isPresent()) {
                    return new ResponseEntity(usuarioUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR')")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try {
            usuarioService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('USUARIO_ELIMINAR_TODO')")
    public ResponseEntity deleteAll() {
        try {
            usuarioService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/departamento/{id}")
    @ApiOperation(value = "Obtiene una lista de Usuarios por Id del Departamento", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(usuarioService.findByDepartamentoId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/departamentoId/{term}")//Puede que aqui sea nombreCompleto
    @ApiOperation(value = "Obtiene una lista de todos los usuarios por departamento", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR')")
    public ResponseEntity<?> findJefeByDepartamento(@PathVariable(value = "term") Long id) {
        try {
            return new ResponseEntity(usuarioService.findJefeByDepartamento(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
