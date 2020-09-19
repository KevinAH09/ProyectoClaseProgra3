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
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.una.tramites.dto.NotaDTO;
import org.una.tramites.entities.Nota;
import org.una.tramites.services.NotaServiceImplementation;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/notas")
@Api(tags = {"Notas"})
public class NotaController {
    @Autowired
    private NotaServiceImplementation notaService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las notas", response = NotaDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Nota>> result = notaService.findAll();
            if (result.isPresent()) {
                List<NotaDTO> resultNotaDTO = MapperUtils.DtoListFromEntityList(result.get(), NotaDTO.class);
                return new ResponseEntity<>(resultNotaDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una nota a travez del id", response = NotaDTO.class, tags = "Notas")
    @PreAuthorize("hasAuthority('NOTA_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Nota> notaFound = notaService.findById(id);
            if (notaFound.isPresent()) {
                NotaDTO resultnotasDto = MapperUtils.DtoFromEntity(notaFound.get(), NotaDTO.class);
                return new ResponseEntity<>(resultnotasDto, HttpStatus.OK);
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
    @PreAuthorize("hasAuthority('NOTA_CREAR')")
    public ResponseEntity<?> create(@RequestBody Nota nota) {
        try {
            Nota notaCreated = notaService.create(nota);
            NotaDTO resultNotaDto = MapperUtils.DtoFromEntity(notaCreated, NotaDTO.class);
            return new ResponseEntity<>(resultNotaDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('NOTA_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Nota notaModified) {
        try {
            Optional<Nota> notaUpdated = notaService.update(notaModified, id);
            if (notaUpdated.isPresent()) {
                NotaDTO notaDto = MapperUtils.DtoFromEntity(notaUpdated.get(), NotaDTO.class);
                return new ResponseEntity<>(notaDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('NOTA_ELIMINAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            notaService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('NOTA_ELIMINAR_TODO')")
    public ResponseEntity<?> deleteAll() {
        try {
            notaService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
