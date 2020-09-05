/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.tramites.dto.PermisosOtorgadosDTO;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.PermisosOtorgados;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.services.IPermisoOtorgadoService;
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
    private IPermisoOtorgadoService permisoOtorgadoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso otorgado", response = PermisosOtorgadosDTO.class, tags = "Permisos_Otorgados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<PermisosOtorgados> permisoOtorgadoFound = permisoOtorgadoService.findById(id);
            if (permisoOtorgadoFound.isPresent()) {
                PermisosOtorgadosDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisosOtorgadosDTO.class);
                return new ResponseEntity<>(permisoOtorgadoDto, HttpStatus.OK);
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
    @ApiOperation(value = "Crea un permiso otorgado", response = PermisosOtorgadosDTO.class, tags = "Permisos_Otorgados")
    public ResponseEntity<?> create(@RequestBody PermisosOtorgados permisoOtorgado) {
        try {
            PermisosOtorgados PermisosOtorgadosCreated = permisoOtorgadoService.create(permisoOtorgado);
            PermisosOtorgadosDTO permisosOtorgadosDTO = MapperUtils.DtoFromEntity(PermisosOtorgadosCreated, PermisosOtorgadosDTO.class);
            return new ResponseEntity<>(permisosOtorgadosDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un permiso otorgado", response = PermisosOtorgadosDTO.class, tags = "Permisos_Otorgados")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PermisosOtorgados permisoOtorgadoModified) {
        try {
            Optional<PermisosOtorgados> permisoOtorgadoServiceUpdated = permisoOtorgadoService.update(permisoOtorgadoModified, id);
            if (permisoOtorgadoServiceUpdated.isPresent()) {
                PermisosOtorgadosDTO usuarioDto = MapperUtils.DtoFromEntity(permisoOtorgadoServiceUpdated.get(), PermisosOtorgadosDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        permisoOtorgadoService.delete(id);
    }

    @DeleteMapping("/")
    public void deleteAll() {
        permisoOtorgadoService.deleteAll();
    }

    @GetMapping("/usuario/{term}")//puede que aqui se usuario_id o usuarioId ????? preguntar al profe que va en el mapping???/
    @ApiOperation(value = "Obtiene una lista de todos los permisos otorgados por usuario", response = PermisosOtorgadosDTO.class, responseContainer = "List", tags = "Permisos_Otorgados")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "term") Long id) {
        try {
            Optional<List<PermisosOtorgados>> result = permisoOtorgadoService.findByUsuarioId(id);
            if (result.isPresent()) {
                List<PermisosOtorgadosDTO> PermisosOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisosOtorgadosDTO.class);
                return new ResponseEntity<>(PermisosOtorgadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permisoId/{term}")//puede que aqui se usuario_id o usuarioId ????? preguntar al profe que va en el mapping???/
    @ApiOperation(value = "Obtiene una lista de todos los permisos otorgados por permisos", response = PermisosOtorgadosDTO.class, responseContainer = "List", tags = "Permisos_Otorgados")
    public ResponseEntity<?> findByPermisoId(@PathVariable(value = "term") Long id) {
        try {
            Optional<List<PermisosOtorgados>> result = permisoOtorgadoService.findByPermisoId(id);
            if (result.isPresent()) {
                List<PermisosOtorgadosDTO> PermisosOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisosOtorgadosDTO.class);
                return new ResponseEntity<>(PermisosOtorgadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha_registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de permisos otorgados entre fechas de registro", response = PermisosOtorgadosDTO.class, tags = "Permisos_Otorgados")
    @ResponseBody
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {

            Optional<List<PermisosOtorgados>> transaccionFound = permisoOtorgadoService.findByFechaRegistroBetween(startDate, endDate);
            if (transaccionFound.isPresent()) {
                List<PermisosOtorgadosDTO> PermisosOtorgadosDTO = MapperUtils.DtoListFromEntityList(transaccionFound.get(), PermisosOtorgadosDTO.class);
                return new ResponseEntity<>(PermisosOtorgadosDTO, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
