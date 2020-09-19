/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
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
import org.una.tramites.dto.PermisoOtorgadoDTO;
import org.una.tramites.services.IPermisoOtorgadoService;

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

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso otorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea un permiso otorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CREAR')")
    public ResponseEntity<?> create(@Valid @RequestBody PermisoOtorgadoDTO permisosOtorgados, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(permisoOtorgadoService.create(permisosOtorgados), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un permiso otorgado", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PermisoOtorgadoDTO PermisoOtorgadoDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<PermisoOtorgadoDTO> PermisoOtorgadoUpdated = permisoOtorgadoService.update(PermisoOtorgadoDTO, id);
                if (PermisoOtorgadoUpdated.isPresent()) {
                    return new ResponseEntity(PermisoOtorgadoUpdated, HttpStatus.OK);
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_ELIMINAR')")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        try {
            permisoOtorgadoService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_ELIMINAR_TODO')")
    public ResponseEntity deleteAll() {
        try {
            permisoOtorgadoService.deleteAll();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/usuario/{term}")//puede que aqui se usuario_id o usuarioId ????? preguntar al profe que va en el mapping???/
    @ApiOperation(value = "Obtiene una lista de todos los permisos otorgados por usuario", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByUsuarioId(@PathVariable(value = "term") Long id) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByUsuarioId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permisoId/{term}")//puede que aqui se usuario_id o usuarioId ????? preguntar al profe que va en el mapping???/
    @ApiOperation(value = "Obtiene una lista de todos los permisos otorgados por permisos", response = PermisoOtorgadoDTO.class, responseContainer = "List", tags = "Permisos_Otorgados")
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByPermisoId(@PathVariable(value = "term") Long id) {
        try {
            return new ResponseEntity(permisoOtorgadoService.findByPermisoId(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha_registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de permisos otorgados entre fechas de registro", response = PermisoOtorgadoDTO.class, tags = "Permisos_Otorgados")
    @ResponseBody
    @PreAuthorize("hasAuthority('PERMISO_OTORGADO_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
            return new ResponseEntity<>(permisoOtorgadoService.findByFechaRegistroBetween(startDate,endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
