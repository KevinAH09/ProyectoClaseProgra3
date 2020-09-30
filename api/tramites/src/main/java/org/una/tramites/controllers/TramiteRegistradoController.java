/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.Comparator;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.TramiteCambioEstadoDTO;
import org.una.tramites.dto.TramiteRegistradoDTO;
import org.una.tramites.services.ITramiteCambioEstadoService;
import org.una.tramites.services.ITramiteRegistradoService;

/**
 *
 * @author colo7
 */
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/tramites_registrados")
@Api(tags = {"Tramites_Registrados"})
public class TramiteRegistradoController {

    @Autowired
    private ITramiteRegistradoService tramiteRegistradoService;

    @Autowired
    private ITramiteCambioEstadoService tramiteCambioEstadoService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<TramiteRegistradoDTO>> resultTramiteRegistrado = tramiteRegistradoService.findAll();

            if (resultTramiteRegistrado.isPresent()) {
                System.out.println(resultTramiteRegistrado.get().size());
                for (int i = 0; i < resultTramiteRegistrado.get().size(); i++) {
                    Optional<List<TramiteCambioEstadoDTO>> resultTramiteCambioestado = tramiteCambioEstadoService.findByTramiteRegistradId(resultTramiteRegistrado.get().get(i).getId());
                    if (!resultTramiteCambioestado.isEmpty()){
                        resultTramiteRegistrado.get().get(i).setTramitesCambioEstados(resultTramiteCambioestado.get());
                        List<TramiteCambioEstadoDTO> cambioEstadoDTOs = new ArrayList<>();
                        cambioEstadoDTOs =  resultTramiteRegistrado.get().get(i).getTramitesCambioEstados();
                        if(cambioEstadoDTOs.size()>1){
                            TramiteCambioEstadoDTO get = cambioEstadoDTOs.stream().max(Comparator.comparing(x-> x.getFechaRegistro())).get();
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual(get);
                        }else if(cambioEstadoDTOs.size()==1){
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual( resultTramiteRegistrado.get().get(i).getTramitesCambioEstados().get(0));
                        }
                    }
                }
            }
            return new ResponseEntity<>(resultTramiteRegistrado, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tramite registrado su id", response = TramiteRegistradoDTO.class, tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(tramiteRegistradoService.findById(id), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_REGISTRAR')")
    public ResponseEntity<?> create(@RequestBody TramiteRegistradoDTO tramiteRegistrado, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity<>(tramiteRegistradoService.create(tramiteRegistrado), HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('TRAMITE_MODIFICAR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TramiteRegistradoDTO tramiteRegistradoModified, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity<>(tramiteRegistradoService.update(tramiteRegistradoModified, id), HttpStatus.OK);

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
            tramiteRegistradoService.delete(id);
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
            tramiteRegistradoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
