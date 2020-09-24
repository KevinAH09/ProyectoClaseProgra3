/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.services.ITramiteCambioEstadoService;

/**
 *
 * @author cfugu
 */
@RestController
@RequestMapping("/tramites_cambio_estado")
@Api(tags = {"Tramites_Cambio_Estado"})
public class TramiteCambioEstadoController {

    @Autowired
    private ITramiteCambioEstadoService tramiteCambioEstadoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites de cambio de estado", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramites_Cambio_Estado")
    @PreAuthorize("hasAuthority('USUARIO_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(tramiteCambioEstadoService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un todos un tramite de cambio de estado por su id", response = TramiteCambioEstadoDTO.class, tags = "Tramites_Cambio_Estado")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramiteCambioEstadoService.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ApiOperation(value = "Permite crear un Usuario", response = TramiteCambioEstadoDTO.class, tags = "Tramites_Cambio_Estado")
    @PreAuthorize("hasAuthority('TRAMITE_REGISTRAR')")
    public ResponseEntity<?> create(@Valid @RequestBody TramiteCambioEstadoDTO tramiteDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(tramiteCambioEstadoService.create(tramiteDTO), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteCambioEstadoDTO tramiteRegistradoModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity<>(tramiteCambioEstadoService.update(tramiteRegistradoModified, id), HttpStatus.OK);

            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TRAMITE_FINALIZAR')")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            tramiteCambioEstadoService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @PreAuthorize("hasAuthority('TRAMITE_FINALIZAR')")
    public ResponseEntity<?> deleteAll() {
        try {
            tramiteCambioEstadoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/tramites_cambio_estado/{term}")//Puede que aqui sea nombreCompleto
//    @ApiOperation(value = "Obtiene una lista de todos los usuarios por departamento", response = TramiteCambioEstadoDTO.class, responseContainer = "List", tags = "Tramites_Cambio_Estado")
//    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
//    public ResponseEntity<?> findByCedulaCliente(@PathVariable(value = "term") String cedula) {
//        try {
//            return new ResponseEntity(tramiteCambioEstadoService.findByCedulaCliente(cedula), HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
