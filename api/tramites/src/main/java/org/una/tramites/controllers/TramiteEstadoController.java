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
import org.una.tramites.dto.TramiteEstadoDTO;
import org.una.tramites.entities.TramiteEstado;
import org.una.tramites.services.ITramiteEstadoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/tramites_estados")
@Api(tags = {"Tramites_Estados"})
public class TramiteEstadoController {
    @Autowired
    private ITramiteEstadoService tramiteEstadoService;
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites estados", response = TramiteEstadoDTO.class, responseContainer = "List", tags = "Tramites_Estados")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteEstado>> result = tramiteEstadoService.findAll();
            if (result.isPresent()) {
                List<TramiteEstadoDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tipo de tramite del id", response = TramiteEstadoDTO.class, tags = "Tramites_Estados")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<TramiteEstado> tramiteEstadoFound = tramiteEstadoService.findById(id);
            if (tramiteEstadoFound.isPresent()) {
                TramiteEstadoDTO tramiteEstadoDTO = MapperUtils.DtoFromEntity(tramiteEstadoFound.get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(tramiteEstadoDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_REGISTRAR')")
    public ResponseEntity<?> create(@RequestBody TramiteEstado tramiteEstado) {
        try {
            TramiteEstado tramiteEstadoCreated = tramiteEstadoService.create(tramiteEstado);
            TramiteEstadoDTO tramiteEstadoDTO = MapperUtils.DtoFromEntity(tramiteEstadoCreated, TramiteEstadoDTO.class);
            return new ResponseEntity<>(tramiteEstadoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteEstado tramiteEstadoModified) {
        try {
            Optional <TramiteEstado> tramiteEstadoUpdated = tramiteEstadoService.update(tramiteEstadoModified, id);
            if (tramiteEstadoUpdated.isPresent()) {
                TramiteEstadoDTO tramiteEstadoDto = MapperUtils.DtoFromEntity(tramiteEstadoUpdated.get(), TramiteEstadoDTO.class);
                return new ResponseEntity<>(tramiteEstadoDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_FINALIZAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteEstadoService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('TRAMITE_ESTADO_FINALIZAR')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteEstadoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
