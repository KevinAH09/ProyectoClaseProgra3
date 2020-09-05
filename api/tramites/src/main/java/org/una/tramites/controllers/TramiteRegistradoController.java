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
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.entities.TramiteRegistrado;
import org.una.tramites.services.ITramiteRegistradoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/tramites_registrados")
@Api(tags = {"Tramites_Registrados"})
public class TramiteRegistradoController {
     @Autowired
    private ITramiteRegistradoService tramiteRegistradoService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteRegistrado>> result = tramiteRegistradoService.findAll();
            if (result.isPresent()) {
                List<TramiteRegistradoDTO> tramiteRegistradoDto = MapperUtils.DtoListFromEntityList(result.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(tramiteRegistradoDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tramite registrado su id", response = TramiteRegistradoDTO.class, tags = "Tramites_Registrados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramiteRegistrado> tramitesFound = tramiteRegistradoService.findById(id);
            if (tramitesFound.isPresent()) {
                TramiteRegistradoDTO tramiteRegistradoDto = MapperUtils.DtoFromEntity(tramitesFound.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(tramiteRegistradoDto, HttpStatus.OK);
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
    public ResponseEntity<?> create(@RequestBody TramiteRegistrado tramiteRegistrado) {
        try {
            TramiteRegistrado tramiteRegistradoCreated = tramiteRegistradoService.create(tramiteRegistrado);
            TramiteRegistradoDTO tramiteRegistradoDto = MapperUtils.DtoFromEntity(tramiteRegistradoCreated, TramiteRegistradoDTO.class);
            return new ResponseEntity<>(tramiteRegistradoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteRegistrado tramiteRegistradoModified) {
        try {
            Optional<TramiteRegistrado> tramiteRegistradoUpdated = tramiteRegistradoService.update(tramiteRegistradoModified, id);
            if (tramiteRegistradoUpdated.isPresent()) {
                TramiteRegistradoDTO tramiteRegistradoDto = MapperUtils.DtoFromEntity(tramiteRegistradoUpdated.get(), TramiteRegistradoDTO.class);
                return new ResponseEntity<>(tramiteRegistradoDto, HttpStatus.OK);

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
            tramiteRegistradoService.delete(id);
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
            tramiteRegistradoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
