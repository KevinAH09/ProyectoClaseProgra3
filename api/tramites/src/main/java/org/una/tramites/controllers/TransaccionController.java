/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.services.ITransaccionService;

/**
 *
 * @author cfugu
 */
@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {
    @Autowired
    private ITransaccionService transaccionService;
    
//    @GetMapping()
//    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
//    public @ResponseBody
//    ResponseEntity<?> findAll() {
//        try {
//            Optional<List<Usuario>> result = usuarioService.findAll();
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
//
//    @GetMapping("/{id}")
//    @ApiOperation(value = "Obtiene un Usuario", response = UsuarioDTO.class, tags = "Usuarios")
//    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
//        try {
//
//            Optional<Usuario> usuarioFound = usuarioService.findById(id);
//            if (usuarioFound.isPresent()) {
//                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/login")
//    @ResponseBody
//    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
//    public ResponseEntity<?> login(@PathVariable(value = "cedula") String cedula, @PathVariable(value = "password") String password) {
//        try {
//            Usuario usuario = new Usuario();
//            usuario.setCedula(cedula);
//            usuario.setPasswordEncriptado(password);
//            Optional<Usuario> usuarioFound = usuarioService.login(usuario);
//            if (usuarioFound.isPresent()) {
//                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
//
//            } else {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//    @GetMapping("/cedula/{term}")
//    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
//    public ResponseEntity<?> findByCedulaAproximate(@PathVariable(value = "term") String term) {
//        try {
//            Optional<List<Usuario>> result = usuarioService.findByCedulaAproximate(term);
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
//
//    @GetMapping("/nombre/{term}")//Puede que aqui sea nombreCompleto
//    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
//    public ResponseEntity<?> findByNombreCompletoAproximateIgnoreCase(@PathVariable(value = "term") String term) {
//        try {
//            Optional<List<Usuario>> result = usuarioService.findByNombreCompletoAproximateIgnoreCase(term);
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
//
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/")
//    @ResponseBody
//    @ApiOperation(value = "Crea un usuario", response = UsuarioDTO.class, tags = "Usuarios")
//    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
//        try {
//            Usuario usuarioCreated = usuarioService.create(usuario);
//            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioCreated, UsuarioDTO.class);
//            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{id}")
//    @ResponseBody
//    @ApiOperation(value = "Modifica un usuario", response = UsuarioDTO.class, tags = "Usuarios")
//    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Usuario usuarioModified) {
//        try {
//            Optional<Usuario> usuarioUpdated = usuarioService.update(usuarioModified, id);
//            if (usuarioUpdated.isPresent()) {
//                UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioUpdated.get(), UsuarioDTO.class);
//                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
//
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable(value = "id") Long id) {
//        usuarioService.delete(id);
//    }
//
//    @DeleteMapping("/")
//    public void deleteAll() {
//        usuarioService.deleteAll();
//    }
//
//    @GetMapping("/departamento_id/{term}")//Puede que aqui sea nombreCompleto
//    @ApiOperation(value = "Obtiene una lista de todos los usuarios por departamento", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
//    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "term") Long id) {
//        try {
//            Optional<List<Usuario>> result = usuarioService.findByDepartamentoId(id);
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
//
//    @GetMapping("/departamentoId/{term}")//Puede que aqui sea nombreCompleto
//    @ApiOperation(value = "Obtiene una lista de todos los usuarios por departamento", response = UsuarioDTO.class, responseContainer = "List", tags = "Usuarios")
//    public ResponseEntity<?> findJefeByDepartamento(@PathVariable(value = "term") Long id) {
//        try {
//            Usuario result = usuarioService.findJefeByDepartamento(id);
//            UsuarioDTO usuarioDto = MapperUtils.DtoFromEntity(result, UsuarioDTO.class);
//            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}