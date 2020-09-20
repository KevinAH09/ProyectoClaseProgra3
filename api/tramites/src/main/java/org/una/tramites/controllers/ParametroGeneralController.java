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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.dto.ParametroGeneralDTO;
import org.una.tramites.entities.Departamento;
import org.una.tramites.entities.ParametroGeneral;
import org.una.tramites.services.IParametroGeneralService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author colo7
 */
@RestController
@RequestMapping("/parametros_generales")
@Api(tags = {"Parametros_Generales"})
public class ParametroGeneralController {
    @Autowired
    private IParametroGeneralService paramGenService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @GetMapping("/nombre/{nombre}")
    @ApiOperation(value = "Obtiene los Paremetro General segun el nombre", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_CONSULTAR')")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre")String nombre) {
        try {
            return new ResponseEntity(paramGenService.findByNombre(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/valor/{valor}")
    @ApiOperation(value = "Obtiene una lista de Parametro General segun el valor", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_CONSULTAR')")
    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor){
        try {
            return new ResponseEntity(paramGenService.findByValor(valor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/estado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Parametro General segun el valor", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_INACTIVAR')")
    public ResponseEntity<?> findByEsatdo(@PathVariable(value = "estado") boolean valor){
        try {
            return new ResponseEntity(paramGenService.findByEstado(valor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de Parametro General segun el valor", response = ParametroGeneralDTO.class, tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long valor){
        try {
            return new ResponseEntity(paramGenService.findById(valor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/descripcion/{descripcion}")
    @ApiOperation(value = "Obtiene una lista de Parametro General segun su descripcion", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_CONSULTAR')")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
        try {
            return new ResponseEntity(paramGenService.findByDescripcion(descripcion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('PARAMETRO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ParametroGeneralDTO ParametroGeneralDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ParametroGeneralDTO> ParametroGeneralUpdated = paramGenService.update(ParametroGeneralDTO, id);
                if (ParametroGeneralUpdated.isPresent()) {
                    return new ResponseEntity(ParametroGeneralUpdated, HttpStatus.OK);
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
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea un permiso", response = ParametroGeneralDTO.class, tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody ParametroGeneralDTO ParametroGeneralDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(paramGenService.create(ParametroGeneralDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Parametro General", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    @PreAuthorize("hasAuthority('PARAMETRO_CONSULTAR')")
    public @ResponseBody ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(paramGenService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
