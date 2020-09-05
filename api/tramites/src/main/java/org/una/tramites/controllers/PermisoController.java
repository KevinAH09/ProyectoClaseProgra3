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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los permisos", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Permiso>> result = permisosService.findAll();
            if (result.isPresent()) {
                List<PermisoDTO> permisoDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso", response = PermisoDTO.class, tags = "Departamentos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Permiso> permisoFound = permisosService.findById(id);
            if (permisoFound.isPresent()) {
                PermisoDTO permisoDTO = MapperUtils.DtoFromEntity(permisoFound.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea un permiso", response = PermisoDTO.class, tags = "Permisos")
    public ResponseEntity<?> create(@RequestBody Permiso permisos) {
        try {
            Permiso permisoCreated = permisosService.create(permisos);
            PermisoDTO permisoDTO = MapperUtils.DtoFromEntity(permisoCreated, PermisoDTO.class);
            return new ResponseEntity<>(permisoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un permiso", response = PermisoDTO.class, tags = "Permisos")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Permiso permisoModified) {
        try {
            Optional<Permiso> permisoUpdated = permisosService.update(permisoModified, id);
            if (permisoUpdated.isPresent()) {
                PermisoDTO permisoDTO = MapperUtils.DtoFromEntity(permisoUpdated.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisoDTO, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{value}")//Puede que aqui sea nombreCompleto
    @ApiOperation(value = "Obtiene una lista de permisos por estado", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "value") boolean value) {
        try {
            Optional<List<Permiso>> result = permisosService.findByEstado(value);
            PermisoDTO permisoDTO = MapperUtils.DtoFromEntity(result, PermisoDTO.class);
            return new ResponseEntity<>(permisoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        permisosService.delete(id);
    }

    @DeleteMapping("/")
    public void deleteAll() {
        permisosService.deleteAll();
    }

    @GetMapping("/fecha_registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de permisos entre fechas de registro", response = PermisoDTO.class, tags = "Permisos")
    @ResponseBody
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {

            Optional<List<Permiso>> permisosFound = permisosService.findByFechaRegistroBetween(startDate, endDate);
            if (permisosFound.isPresent()) {
                List<PermisoDTO> permisoDTO = MapperUtils.DtoListFromEntityList(permisosFound.get(), PermisoDTO.class);
                return new ResponseEntity<>(permisoDTO, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
