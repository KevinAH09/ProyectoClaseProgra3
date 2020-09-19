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
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.PermisoOtorgado;
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
public class PermisoOtorgadoController {

    @Autowired
    private IPermisoOtorgadoService permisoOtorgadoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso otorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
   @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<PermisoOtorgado> permisoOtorgadoFound = permisoOtorgadoService.findById(id);
            if (permisoOtorgadoFound.isPresent()) {
                PermisoOtorgadoDTO permisoOtorgadoDto = MapperUtils.DtoFromEntity(permisoOtorgadoFound.get(), PermisoOtorgadoDTO.class);
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
    @ApiOperation(value = "Crea un permiso otorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
   @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CREAR')")
    public ResponseEntity<?> create(@RequestBody PermisoOtorgado permisoOtorgado) {
        try {
            PermisoOtorgado PermisosOtorgadosCreated = permisoOtorgadoService.create(permisoOtorgado);
            PermisoOtorgadoDTO permisosOtorgadosDTO = MapperUtils.DtoFromEntity(PermisosOtorgadosCreated, PermisoOtorgadoDTO.class);
            return new ResponseEntity<>(permisosOtorgadosDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un permiso otorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody PermisoOtorgado permisoOtorgadoModified) {
        try {
            Optional<PermisoOtorgado> permisoOtorgadoServiceUpdated = permisoOtorgadoService.update(permisoOtorgadoModified, id);
            if (permisoOtorgadoServiceUpdated.isPresent()) {
                PermisoOtorgadoDTO usuarioDto = MapperUtils.DtoFromEntity(permisoOtorgadoServiceUpdated.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
   @PreAuthorize("hasAuthority('PERMISO_OTORGADO_ELIMINAR')")
    public void delete(@PathVariable(value = "id") Long id) {
        permisoOtorgadoService.delete(id);
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_ELIMINAR_TODO')")
    public void deleteAll() {
        permisoOtorgadoService.deleteAll();
    }

    @GetMapping("/usuario/{term}")//puede que aqui se usuario_id o usuarioId ????? preguntar al profe que va en el mapping???/
    @ApiOperation(value = "Obtiene una lista de todos los permisos otorgados por usuario", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "term") Long id) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByUsuarioId(id);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> PermisosOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(PermisosOtorgadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permisoId/{term}")//puede que aqui se usuario_id o usuarioId ????? preguntar al profe que va en el mapping???/
    @ApiOperation(value = "Obtiene una lista de todos los permisos otorgados por permisos", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByPermisoId(@PathVariable(value = "term") Long id) {
        try {
            Optional<List<PermisoOtorgado>> result = permisoOtorgadoService.findByPermisoId(id);
            if (result.isPresent()) {
                List<PermisoOtorgadoDTO> PermisosOtorgadosDTO = MapperUtils.DtoListFromEntityList(result.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(PermisosOtorgadosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha_registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de permisos otorgados entre fechas de registro", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
    @ResponseBody
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {

            Optional<List<PermisoOtorgado>> transaccionFound = permisoOtorgadoService.findByFechaRegistroBetween(startDate, endDate);
            if (transaccionFound.isPresent()) {
                List<PermisoOtorgadoDTO> PermisosOtorgadosDTO = MapperUtils.DtoListFromEntityList(transaccionFound.get(), PermisoOtorgadoDTO.class);
                return new ResponseEntity<>(PermisosOtorgadosDTO, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
