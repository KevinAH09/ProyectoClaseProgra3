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
import org.una.tramites.dto.RequisitoPresentadoDTO;
import org.una.tramites.entities.RequisitoPresentado;
import org.una.tramites.services.IRequisitoPresentadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/requisitos_presentados")
@Api(tags = {"Requisitos_Presentados"})
public class RequisitoPresentadoController {
    
    @Autowired
    IRequisitoPresentadoService requisitoPresentadoService;
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un requisito presentado", response = RequisitoPresentadoDTO.class, tags = "Requisitos Presentados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<RequisitoPresentado> requisitoPresentadoFound = requisitoPresentadoService.findById(id);
            if (requisitoPresentadoFound.isPresent()) {
                RequisitoPresentadoDTO requisitoPresentadoDto = MapperUtils.DtoFromEntity(requisitoPresentadoFound.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(requisitoPresentadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los requisitos presentados", response = RequisitoPresentadoDTO.class, responseContainer = "List", tags = "Requisitos Presentados")
    public @ResponseBody ResponseEntity<?> findAll() {
        try {
            Optional<List<RequisitoPresentado>> result = requisitoPresentadoService.findAll();
            if (result.isPresent()) {
                List<RequisitoPresentadoDTO> requisitoPresentadoDTO = MapperUtils.DtoListFromEntityList(result.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(requisitoPresentadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody RequisitoPresentado requisitoPresentado) {
        try {
            RequisitoPresentado requisitoPresentadoCreated = requisitoPresentadoService.create(requisitoPresentado);
            RequisitoPresentadoDTO requisitoPresentadoDto = MapperUtils.DtoFromEntity(requisitoPresentadoCreated, RequisitoPresentadoDTO.class);
            return new ResponseEntity<>(requisitoPresentadoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RequisitoPresentado reqPresentadoModified) {
        try {
            Optional<RequisitoPresentado> reqPresentadoUpdated = requisitoPresentadoService.update(reqPresentadoModified, id);
            if (reqPresentadoUpdated.isPresent()) {
                RequisitoPresentadoDTO reqPresentadoDto = MapperUtils.DtoFromEntity(reqPresentadoUpdated.get(), RequisitoPresentadoDTO.class);
                return new ResponseEntity<>(reqPresentadoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            requisitoPresentadoService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() {
        try {
            requisitoPresentadoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
