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
    
    @GetMapping("/nombre/{nombre}")
    @ApiOperation(value = "Obtiene los Paremetro General segun el nombre", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre")String nombre) {
        try{
            Optional<List<ParametroGeneral>> result = paramGenService.findByNombre(nombre);
            if(result.isPresent()){
                List<ParametroGeneralDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/valor/{valor}")
    @ApiOperation(value = "Obtiene una lista de Parametro General segun el valor", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor){
        try{
            Optional<List<ParametroGeneral>> result = paramGenService.findByValor(valor);
            if(result.isPresent()){
                List<ParametroGeneralDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/estado/{estado}")
    @ApiOperation(value = "Obtiene una lista de Parametro General segun el valor", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByEsatdo(@PathVariable(value = "estado") boolean valor){
        try{
            Optional<List<ParametroGeneral>> result = paramGenService.findByEstado(valor);
            if(result.isPresent()){
                List<ParametroGeneralDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una lista de Parametro General segun el valor", response = ParametroGeneralDTO.class, tags = "Parametros_Generales")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long valor){
        try{
            Optional<ParametroGeneral> parametroGeneralFound = paramGenService.findById(valor);
            if (parametroGeneralFound.isPresent()) {
                ParametroGeneral parametroGeneral = MapperUtils.DtoFromEntity(parametroGeneralFound.get(), ParametroGeneral.class);
                return new ResponseEntity<>(parametroGeneral, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/descripcion/{descripcion}")
    @ApiOperation(value = "Obtiene una lista de Parametro General segun su descripcion", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public ResponseEntity<?> findByDescripcion(@PathVariable(value = "descripcion")String descripcion){
        try{
            Optional<List<ParametroGeneral>> result = paramGenService.findByDescripcion(descripcion);
            if(result.isPresent()){
                List<ParametroGeneralDTO> parametroGeneralDto = MapperUtils.DtoListFromEntityList(result.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(parametroGeneralDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametroGeneral parGen) {
        try {
            Optional<ParametroGeneral> parametroGeneralUpdate = paramGenService.update(parGen, id);
            if (parametroGeneralUpdate.isPresent()) {
                ParametroGeneralDTO parametroGeneralDto = MapperUtils.DtoFromEntity(parametroGeneralUpdate.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(parametroGeneralDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea un permiso", response = ParametroGeneralDTO.class, tags = "Parametros_Generales")
    public ResponseEntity<?> create(@RequestBody ParametroGeneral parametros) {
        try {
            Optional<ParametroGeneral> permisoCreated = paramGenService.create(parametros);
            ParametroGeneralDTO permisoDTO = MapperUtils.DtoFromEntity(permisoCreated.get(), ParametroGeneralDTO.class);
            return new ResponseEntity<>(permisoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Parametro General", response = ParametroGeneralDTO.class, responseContainer = "List", tags = "Parametros_Generales")
    public @ResponseBody ResponseEntity<?> findAll() {
        try {
            Optional<List<ParametroGeneral>> result = paramGenService.findAll();
            if (result.isPresent()) {
                List<ParametroGeneralDTO> resultParametroGeneralDTO = MapperUtils.DtoListFromEntityList(result.get(), ParametroGeneralDTO.class);
                return new ResponseEntity<>(resultParametroGeneralDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
