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
import org.una.tramites.dto.TramiteTipoDTO;
import org.una.tramites.entities.TramiteTipo;
import org.una.tramites.services.ITramiteTipoService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/tramites_Tipos")
@Api(tags = {"Tramites_Tipos"})
public class TramiteTipoController {

    @Autowired
    private ITramiteTipoService tramiteTipoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tipo de tramite", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(tramiteTipoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tipo de tramie", response = TramiteTipoDTO.class, tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            return new ResponseEntity<>(tramiteTipoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los tipo de tramites por estado", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_ESTADO')")
    public ResponseEntity<?> findByEstadoContaining(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(tramiteTipoService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/departamento_id/{term}")
    @ApiOperation(value = "Obtiene una lista de todos los tipos de tramites por departamento", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_CONSULTAR')")
    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "term") Long id) {
        try {
            return new ResponseEntity<>(tramiteTipoService.findByDepartamentoId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea un tipo de tramite", response = TramiteTipoDTO.class, tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_REGISTRAR')")
    public ResponseEntity<?> create(@RequestBody TramiteTipoDTO tramiteTipo, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                TramiteTipoDTO tramiteTipoDto = MapperUtils.DtoFromEntity(tramiteTipoService.create(tramiteTipo), TramiteTipoDTO.class);
                return new ResponseEntity<>(tramiteTipoDto, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un tipo de tramite", response = TramiteTipoDTO.class, tags = "Tramites_Tipos")
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteTipoDTO tramiteTipoModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity<>(tramiteTipoService.update(tramiteTipoModified, id), HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_FINALIZAR')")
    public void delete(@PathVariable(value = "id") Long id) {
        tramiteTipoService.delete(id);
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('TRAMITE_TIPO_FINALIZAR')")
    public void deleteAll() {
        tramiteTipoService.deleteAll();
    }
}
