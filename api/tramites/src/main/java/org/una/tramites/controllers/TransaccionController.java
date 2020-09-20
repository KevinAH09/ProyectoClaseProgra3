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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TransaccionDTO;
import org.una.tramites.entities.Transaccion;
import org.una.tramites.services.ITransaccionService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cfugu
 */
@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionController {

    @Autowired
    private ITransaccionService transaccionService;
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRAN_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

                return new ResponseEntity<>(transaccionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha_registro/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de transacciones entre fechas de registro", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
   @PreAuthorize("hasAuthority('TRAN_CONSULTAR')")
    public ResponseEntity<?> findByFechaRegistroBetween(@PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {

                return new ResponseEntity<>(transaccionService.findByFechaRegistroBetween(startDate, endDate), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @ApiOperation(value = "Crea una transaccion", response = TransaccionDTO.class, tags = "Transacciones")
    @PreAuthorize("hasAuthority('TRAN_CREAR')")
    public ResponseEntity<?> create(@RequestBody TransaccionDTO transaccion, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
            return new ResponseEntity<>(transaccionService.create(transaccion), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }else{
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{usuario}/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de transacciones entre fechas de registro por un usuario", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAN_CONSULTAR')")
    public ResponseEntity<?> findByUsuarioIdAndFechaRegistroBetween(@PathVariable(value = "usuario") Long usuario, @PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {
                return new ResponseEntity<>(transaccionService.findByUsuarioIdAndFechaRegistroBetween(usuario, startDate, endDate), HttpStatus.OK);

            
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{permiso}/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de transacciones entre fechas de registro por permiso", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAN_CONSULTAR')")
    public ResponseEntity<?> findByPermisoIdAndFechaRegistroBetween(@PathVariable(value = "permiso") Long permiso, @PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {

                return new ResponseEntity<>(transaccionService.findByPermisoIdAndFechaRegistroBetween(permiso, startDate, endDate), HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{objeto}/{inicio}/{fin}")
    @ApiOperation(value = "Obtiene una lista de transacciones entre fechas de registro por objeto", response = TransaccionDTO.class, tags = "Transacciones")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAN_CONSULTAR')")
    public ResponseEntity<?> findByObjetoAndFechaRegistroBetween(@PathVariable(value = "objeto") String objeto, @PathVariable(value = "inicio") Date startDate, @PathVariable(value = "fin") Date endDate) {
        try {

                return new ResponseEntity<>(transaccionService.findByObjetoAndFechaRegistroBetween(objeto, startDate, endDate), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
