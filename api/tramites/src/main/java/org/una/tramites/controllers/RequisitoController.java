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
import org.una.tramites.dto.RequisitoDTO;
import org.una.tramites.entities.Requisito;
import org.una.tramites.services.IRequisitoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/requisitos")
@Api(tags = {"Requisitos"})
public class RequisitoController {
    
    @Autowired
    private IRequisitoService requisitoService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los requisitos", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Requisito>> result = requisitoService.findAll();
            if (result.isPresent()) {
                List<RequisitoDTO> requisitoDto = MapperUtils.DtoListFromEntityList(result.get(), RequisitoDTO.class);
                return new ResponseEntity<>(requisitoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Requisito", response = RequisitoDTO.class, tags = "Requisitos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Requisito> requisitoFound = requisitoService.findById(id);
            if (requisitoFound.isPresent()) {
                RequisitoDTO requisitoDto = MapperUtils.DtoFromEntity(requisitoFound.get(), RequisitoDTO.class);
                return new ResponseEntity<>(requisitoDto, HttpStatus.OK);
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
    @ApiOperation(value = "Crea un requisito", response = RequisitoDTO.class, tags = "Requisitos")
    public ResponseEntity<?> create(@RequestBody Requisito requisito) {
        try {
            Requisito requisitoCreated = requisitoService.create(requisito);
            RequisitoDTO requisitoDto = MapperUtils.DtoFromEntity(requisitoCreated, RequisitoDTO.class);
            return new ResponseEntity<>(requisitoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los requisitos por estado", response = RequisitoDTO.class, responseContainer = "List", tags = "Requisitos")
    public ResponseEntity<?> findByEstadoContaining(@PathVariable(value = "term") boolean term) {
        try {
            Optional<List<Requisito>> result = requisitoService.findByEstadoContaining(term);
            if (result.isPresent()) {
                List<RequisitoDTO> requisitosDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitoDTO.class);
                return new ResponseEntity<>(requisitosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un requisitos", response = RequisitoDTO.class, tags = "Requisitos")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Requisito requisito) {
        try {
            Optional<Requisito> requisitoUpdated = requisitoService.update(requisito, id);
            if (requisitoUpdated.isPresent()) {
                RequisitoDTO requisitoDto = MapperUtils.DtoFromEntity(requisitoUpdated.get(), RequisitoDTO.class);
                return new ResponseEntity<>(requisitoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        requisitoService.delete(id);
    }

    @DeleteMapping("/")
    public void deleteAll() {
        requisitoService.deleteAll();
    }

}
