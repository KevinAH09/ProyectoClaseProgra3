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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.DepartamentoDTO;
import org.una.tramites.dto.PermisosOtorgadosDTO;
import org.una.tramites.services.IPermisosOtorgadosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cfugu
 */
@RestController
@RequestMapping("/Permisos_Otorgados")
@Api(tags = {"Permisos_Otorgados"})
public class PermisosOtorgadosController {
    @Autowired
    private IPermisosOtorgadosService permisoOtorgadoService;
//    public Optional<PermisosOtorgados> findById(Long usuarioId);
//
//    public Optional<List<PermisosOtorgados>> findByUsuarioId(Long usuarioId);
//
//    public Optional<List<PermisosOtorgados>> findByPermisoId(Long permisoId);
//
//    public Optional<List<PermisosOtorgados>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado);
//
//    public Optional<List<PermisosOtorgados>> findByPermisoIdAndEstado(Long permisoId, boolean estado);
//
//    public Optional<List<PermisosOtorgados>> findByFechaRegistroBetween(Date startDate, Date endDate);
    
    
//    @GetMapping()
//    @ApiOperation(value = "Obtiene una lista de todos los departamentos", response = PermisosOtorgadosDTO.class, responseContainer = "List", tags = "Departamentos")
//    public @ResponseBody
//    ResponseEntity<?> findAll() {
//        try {
//            Optional<List<PermisosOtorgados>> result = departamentoService.findAll();
//            if (result.isPresent()) {
//                List<DepartamentoDTO> departamentoDTO = MapperUtils.DtoListFromEntityList(result.get(), DepartamentoDTO.class);
//                return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/{id}")
//    @ApiOperation(value = "Obtiene un departamento", response = DepartamentoDTO.class, tags = "Departamentos")
//    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
//        try {
//
//            Optional<Departamento> departamentoFound = departamentoService.findById(id);
//            if (departamentoFound.isPresent()) {
//                DepartamentoDTO departamentoDto = MapperUtils.DtoFromEntity(departamentoFound.get(), DepartamentoDTO.class);
//                return new ResponseEntity<>(departamentoDto, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/nombre/{term}")
//    @ApiOperation(value = "Obtiene una lista de todos los departamentos", response = DepartamentoDTO.class, responseContainer = "List", tags = "Departamentos")
//    public ResponseEntity<?> findByNombreproximateIgnoreCase(@PathVariable(value = "term") String term) {
//        try {
//            Optional<List<Departamento>> result = departamentoService.findByNombreAproximateIgnoreCase(term);
//            if (result.isPresent()) {
//                List<DepartamentoDTO> departamentosDTO = MapperUtils.DtoListFromEntityList(result.get(), DepartamentoDTO.class);
//                return new ResponseEntity<>(departamentosDTO, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/")
//    @ResponseBody
//    @ApiOperation(value = "Crea un departamento", response = DepartamentoDTO.class, tags = "Departamentos")
//    public ResponseEntity<?> create(@RequestBody Departamento departamento) {
//        try {
//            Departamento departamentoCreated = departamentoService.create(departamento);
//            DepartamentoDTO departamentoDTO = MapperUtils.DtoFromEntity(departamentoCreated, DepartamentoDTO.class);
//            return new ResponseEntity<>(departamentoDTO, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/{id}")
//    @ResponseBody
//    @ApiOperation(value = "Modifica un departamento", response = DepartamentoDTO.class, tags = "Departamentos")
//    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Departamento departamentoModified) {
//        try {
//            Optional<Departamento> departamentoUpdated = departamentoService.update(departamentoModified, id);
//            if (departamentoUpdated.isPresent()) {
//                DepartamentoDTO departamentoDTO = MapperUtils.DtoFromEntity(departamentoUpdated.get(), DepartamentoDTO.class);
//                return new ResponseEntity<>(departamentoDTO, HttpStatus.OK);
//
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//     @DeleteMapping("/{id}")
//    public void delete(@PathVariable(value = "id") Long id) {
//        usuarioService.delete(id);
//    }
//
//    @DeleteMapping("/")
//    public void deleteAll() {
//        usuarioService.deleteAll();
//    }
}
