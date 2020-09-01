/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package org.una.tramites.controllers;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.una.tramites.dto.TramiteTipoDTO;
//import org.una.tramites.entities.TramiteTipo;
//import org.una.tramites.services.ITramiteTipoService;
//import org.una.tramites.utils.MapperUtils;
//
///**
// *
// * @author Bosco
// */
//  @RestController
//@RequestMapping("/tramites_Tipos")
//@Api(tags = {"Tramites_Tipos"})
//public class TramiteTipoController {
//  
//
//
//    @Autowired
//    private ITramiteTipoService tramiteTipoService;
//
//    @GetMapping()
//    @ApiOperation(value = "Obtiene una lista de todos los tipo de tramite", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
//    public @ResponseBody
//    ResponseEntity<?> findAll() {
//        try {
//            Optional<List<TramiteTipo>> result = tramiteTipoService.findAll();
//            if (result.isPresent()) {
//                List<TramiteTipoDTO> tramiteTipoDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteTipoDTO.class);
//                return new ResponseEntity<>(tramiteTipoDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/{id}")
//    @ApiOperation(value = "Obtiene un tipo de tramie", response = TramiteTipoDTO.class, tags = "Tramites_Tipos")
//    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
//        try {
//
//            Optional<TramiteTipo> tramiteTipoFound = tramiteTipoService.findById(id);
//            if (tramiteTipoFound.isPresent()) {
//                TramiteTipoDTO tramiteTipoDTO = MapperUtils.DtoFromEntity(tramiteTipoFound.get(), TramiteTipoDTO.class);
//                return new ResponseEntity<>(tramiteTipoDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//@GetMapping("/estado/{term}")
//    @ApiOperation(value = "Obtiene una lista de todos los tipo de tramites por estado", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
//    public ResponseEntity<?> findByEstadoContaining(@PathVariable(value = "term") boolean term) {
//        try {
//            Optional<List<TramiteTipo>> result = tramiteTipoService.findByEstadoContaining(term);
//            if (result.isPresent()) {
//                List<TramiteTipoDTO> TramiteTipoDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteTipoDTO.class);
//                return new ResponseEntity<>(TramiteTipoDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @GetMapping("/departamento_id/{term}")
//    @ApiOperation(value = "Obtiene una lista de todos los tipos de tramites por departamento", response = TramiteTipoDTO.class, responseContainer = "List", tags = "Tramites_Tipos")
//    public ResponseEntity<?> findByDepartamentoId(@PathVariable(value = "term") Long id) {
//        try {
//            Optional<List<TramiteTipo>> result = tramiteTipoService.findByDepartamentoId(id);
//            if (result.isPresent()) {
//                List<TramiteTipoDTO> tramieTipoDTO = MapperUtils.DtoListFromEntityList(result.get(), TramiteTipoDTO.class);
//                return new ResponseEntity<>(tramieTipoDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/")
//    @ResponseBody
//    @ApiOperation(value = "Crea un tipo de tramite", response = TramiteTipoDTO.class, tags = "Tramites_Tipos")
//    public ResponseEntity<?> create(@RequestBody TramiteTipo tramiteTipo) {
//        try {
//            TramiteTipo tramiteTipoCreated = tramiteTipoService.create(tramiteTipo);
//            TramiteTipoDTO tramiteTipoDto = MapperUtils.DtoFromEntity(tramiteTipoCreated, TramiteTipoDTO.class);
//            return new ResponseEntity<>(tramiteTipoDto, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{id}")
//    @ResponseBody
//    @ApiOperation(value = "Modifica un tipo de tramite", response = TramiteTipoDTO.class, tags = "Tramites_Tipos")
//    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteTipo tramiteTipoModified) {
//        try {
//            Optional<TramiteTipo> tramiteTipoUpdated = tramiteTipoService.update(tramiteTipoModified, id);
//            if (tramiteTipoUpdated.isPresent()) {
//                TramiteTipoDTO tramiteTipoDTO = MapperUtils.DtoFromEntity(tramiteTipoUpdated.get(), TramiteTipoDTO.class);
//                return new ResponseEntity<>(tramiteTipoDTO, HttpStatus.OK);
//
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable(value = "id") Long id) {
//        tramiteTipoService.delete(id);
//    }
//
//    @DeleteMapping("/")
//    public void deleteAll() {
//        tramiteTipoService.deleteAll();
//    }
//  }