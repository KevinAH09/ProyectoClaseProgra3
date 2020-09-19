/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.PermisoDTO;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Permiso;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.services.IPermisoService;

/**
 *
 * @author cfugu
 */
@RestController
@RequestMapping("/permisos")
@Api(tags = {"Permisos"})
public class PermisoController {

    @Autowired
    private IPermisoService permisosService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los permisos", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(permisosService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso", response = PermisoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(permisosService.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea un permiso", response = PermisoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody PermisoDTO PermisoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(permisosService.create(PermisoDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un permiso", response = PermisoDTO.class, tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PermisoDTO PermisoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<PermisoDTO> permisoUpdated = permisosService.update(PermisoDTO, id);
                if (permisoUpdated.isPresent()) {
                    return new ResponseEntity(permisoUpdated, HttpStatus.OK);
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

    @GetMapping("/estado/{value}")//Puede que aqui sea nombreCompleto
    @ApiOperation(value = "Obtiene una lista de permisos por estado", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_INACTIVAR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "value") boolean value) {
        try {
            return new ResponseEntity<>(permisosService.findByEstado(value), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/codigo/{value}")//Puede que aqui sea nombreCompleto
    @ApiOperation(value = "Obtiene un permiso por codigo", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    @PreAuthorize("hasAuthority('PERMISO_CONSULTAR')")
    public ResponseEntity<?> findByCodigo(@PathVariable(value = "value") String value) {
        try {
            return new ResponseEntity<>(permisosService.findByCodigo(value), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISO_ELIMINAR')")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try {
            permisosService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('PERMISO_ELIMINAR_TODO')")
    public ResponseEntity deleteAll() {
        try {
            permisosService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha_registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de permisos entre fechas de registro", response = PermisoDTO.class, tags = "Permisos")
    @ResponseBody
    @PreAuthorize("hasAuthority('PERMISO_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            return new ResponseEntity<>(permisosService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
