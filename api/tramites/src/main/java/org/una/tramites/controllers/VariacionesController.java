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
import org.una.tramites.dto.VariacionesDTO;
import org.una.tramites.entities.Variaciones;
import org.una.tramites.services.IVariacionesService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/variaciones")
@Api(tags = {"Variaciones"})
public class VariacionesController {
    @Autowired
    private IVariacionesService variacionesService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todas las variaciones", response = VariacionesDTO.class, responseContainer = "List", tags = "Variaciones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Variaciones>> result = variacionesService.findAll();
            if (result.isPresent()) {
                List<VariacionesDTO> variacionesDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionesDTO.class);
                return new ResponseEntity<>(variacionesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una Variacion", response = VariacionesDTO.class, tags = "Variaciones")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Variaciones> variacionesFound = variacionesService.findById(id);
            if (variacionesFound.isPresent()) {
                VariacionesDTO usuarioDto = MapperUtils.DtoFromEntity(variacionesFound.get(), VariacionesDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todas las variaciones por estado", response = VariacionesDTO.class, responseContainer = "List", tags = "Variaciones")
    public ResponseEntity<?> findByEstadoContaining(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Variaciones>> result = variacionesService.findByEstadoContaining(term);
            if (result.isPresent()) {
                List<VariacionesDTO> variacionesDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionesDTO.class);
                return new ResponseEntity<>(variacionesDTO, HttpStatus.OK);
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
    @ApiOperation(value = "Crea una variacion", response = VariacionesDTO.class, tags = "Variaciones")
    public ResponseEntity<?> create(@RequestBody Variaciones variacion) {
        try {
            Variaciones variacionCreated = variacionesService.create(variacion);
            VariacionesDTO variacionesDTO = MapperUtils.DtoFromEntity(variacionCreated, VariacionesDTO.class);
            return new ResponseEntity<>(variacionesDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica una variacion", response = VariacionesDTO.class, tags = "Variaciones")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Variaciones variacionModified) {
        try {
            Optional<Variaciones> usuarioUpdated = variacionesService.update(variacionModified, id);
            if (usuarioUpdated.isPresent()) {
                VariacionesDTO variacionDto = MapperUtils.DtoFromEntity(usuarioUpdated.get(), VariacionesDTO.class);
                return new ResponseEntity<>(variacionDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable(value = "id") Long id) {
//        variacionesService.delete(id);
//    }
//
//    @DeleteMapping("/")
//    public void deleteAll() {
//        variacionesService.deleteAll();
//    }

    @GetMapping("/tramiteTipo_id/{term}")//Puede que aqui sea nombreCompleto
    @ApiOperation(value = "Obtiene una lista de todas las variaciones por tipo de tramite", response = VariacionesDTO.class, responseContainer = "List", tags = "Variaciones")
    public ResponseEntity<?> findByTramiteTipoId(@PathVariable(value = "term") Long id) {
        try {
            Optional<List<Variaciones>> result = variacionesService.findByTramiteTipoId(id);
            if (result.isPresent()) {
                List<VariacionesDTO> variacionesDTO = MapperUtils.DtoListFromEntityList(result.get(), VariacionesDTO.class);
                return new ResponseEntity<>(variacionesDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
