/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.una.tramites.entities.Permisos;
import org.una.tramites.services.IPermisosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cfugu
 */
@RestController
@RequestMapping("/permisos")
@Api(tags = {"Permisos"})
public class PermisosController {

    @Autowired
    private IPermisosService permisosService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los permisos", response = PermisoDTO.class, responseContainer = "List", tags = "Permisos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Permisos>> result = permisosService.findAll();
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

            Optional<Permisos> permisoFound = permisosService.findById(id);
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
    public ResponseEntity<?> create(@RequestBody Permisos permisos) {
        try {
            Permisos permisoCreated = permisosService.create(permisos);
            PermisoDTO permisoDTO = MapperUtils.DtoFromEntity(permisoCreated, PermisoDTO.class);
            return new ResponseEntity<>(permisoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un permiso", response = PermisoDTO.class, tags = "Permisos")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Permisos permisoModified) {
        try {
            Optional<Permisos> permisoUpdated = permisosService.update(permisoModified, id);
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

//    public Optional<List<Permisos>> findByEstado(boolean estado);
//
//    public Optional<List<Permisos>> findByFechaRegistroBetween(Date startDate, Date endDate);

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        permisosService.delete(id);
    }

    @DeleteMapping("/")
    public void deleteAll() {
        permisosService.deleteAll();
    }
    
}
