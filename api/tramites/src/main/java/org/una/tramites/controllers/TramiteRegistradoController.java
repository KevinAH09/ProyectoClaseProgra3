/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
            Optional<List<TramiteRegistradoDTO>> resultTramite = null;
            if (resultTramiteRegistrado.isPresent()) {
                System.out.println(resultTramiteRegistrado.get().size());
                for (int i = 0; i < resultTramiteRegistrado.get().size(); i++) {
                    Optional<List<TramiteCambioEstadoDTO>> resultTramiteCambioestado = tramiteCambioEstadoService.findByTramiteRegistradId(resultTramiteRegistrado.get().get(i).getId());
                    if (!resultTramiteCambioestado.isEmpty()) {
                        resultTramiteRegistrado.get().get(i).setTramitesCambioEstados(resultTramiteCambioestado.get());
                        List<TramiteCambioEstadoDTO> cambioEstadoDTOs = new ArrayList<>();
                        cambioEstadoDTOs = resultTramiteRegistrado.get().get(i).getTramitesCambioEstados();
                        if (cambioEstadoDTOs.size() > 1) {
                            TramiteCambioEstadoDTO actualCambioEsatdo = cambioEstadoDTOs.stream().max(Comparator.comparing(x -> x.getFechaRegistro())).get();
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual(actualCambioEsatdo);
                        } else if (cambioEstadoDTOs.size() == 1) {
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual(resultTramiteRegistrado.get().get(i).getTramitesCambioEstados().get(0));
                        }
                    }
                }
            }
            for (TramiteRegistradoDTO tramiteRegistradoDTO : resultTramiteRegistrado.get()) {
                
                System.out.println(tramiteRegistradoDTO);
                
            }
            System.out.println(resultTramiteRegistrado.get().get(0));
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
            Optional<TramiteRegistradoDTO> resultTramiteRegistrado = tramiteRegistradoService.findById(id);
            List<TramiteRegistradoDTO> resultList = new ArrayList<>();
            if (resultTramiteRegistrado.isPresent()) {

                Optional<List<TramiteCambioEstadoDTO>> resultTramiteCambioestado = tramiteCambioEstadoService.findByTramiteRegistradId(resultTramiteRegistrado.get().getId());
                if (!resultTramiteCambioestado.isEmpty()) {
                    resultTramiteRegistrado.get().setTramitesCambioEstados(resultTramiteCambioestado.get());
                    List<TramiteCambioEstadoDTO> cambioEstadoDTOs = new ArrayList<>();
                    cambioEstadoDTOs = resultTramiteRegistrado.get().getTramitesCambioEstados();
                    if (cambioEstadoDTOs.size() > 1) {
                        TramiteCambioEstadoDTO actualCambioEsatdo = cambioEstadoDTOs.stream().max(Comparator.comparing(x -> x.getFechaRegistro())).get();
                        resultTramiteRegistrado.get().setCambioEstadoActual(actualCambioEsatdo);
                    } else if (cambioEstadoDTOs.size() == 1) {
                        resultTramiteRegistrado.get().setCambioEstadoActual(resultTramiteRegistrado.get().getTramitesCambioEstados().get(0));
                    }

                }
                resultList.add(resultTramiteRegistrado.get());
            }
            return new ResponseEntity<>(resultList, HttpStatus.OK);

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

    @GetMapping("/cedula/{ced}")
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados por cedula", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR_TODO')")
    public @ResponseBody
    ResponseEntity<?> findClienteCedula(@PathVariable(value = "ced") String cedula) {
        try {
            Optional<List<TramiteRegistradoDTO>> resultTramiteRegistrado = tramiteRegistradoService.findByClientesCedula(cedula);

            if (resultTramiteRegistrado.isPresent()) {
                System.out.println(resultTramiteRegistrado.get().size());
                for (int i = 0; i < resultTramiteRegistrado.get().size(); i++) {
                    Optional<List<TramiteCambioEstadoDTO>> resultTramiteCambioestado = tramiteCambioEstadoService.findByTramiteRegistradId(resultTramiteRegistrado.get().get(i).getId());
                    if (!resultTramiteCambioestado.isEmpty()) {
                        resultTramiteRegistrado.get().get(i).setTramitesCambioEstados(resultTramiteCambioestado.get());
                        List<TramiteCambioEstadoDTO> cambioEstadoDTOs = new ArrayList<>();
                        cambioEstadoDTOs = resultTramiteRegistrado.get().get(i).getTramitesCambioEstados();
                        if (cambioEstadoDTOs.size() > 1) {
                            TramiteCambioEstadoDTO actualCambioEsatdo = cambioEstadoDTOs.stream().max(Comparator.comparing(x -> x.getFechaRegistro())).get();
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual(actualCambioEsatdo);
                        } else if (cambioEstadoDTOs.size() == 1) {
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual(resultTramiteRegistrado.get().get(i).getTramitesCambioEstados().get(0));
                        }
                    }
                }
            }
            return new ResponseEntity<>(resultTramiteRegistrado, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado/{estado}")
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados por cedula", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public @ResponseBody
    ResponseEntity<?> findByEstadoActualTramite(@PathVariable(value = "estado") String estado) {
        try {
            Optional<List<TramiteRegistradoDTO>> resultTramiteRegistrado = tramiteRegistradoService.findAll();

            if (resultTramiteRegistrado.isPresent()) {
                System.out.println(resultTramiteRegistrado.get().size());
                for (int i = 0; i < resultTramiteRegistrado.get().size(); i++) {
                    Optional<List<TramiteCambioEstadoDTO>> resultTramiteCambioestado = tramiteCambioEstadoService.findByTramiteRegistradId(resultTramiteRegistrado.get().get(i).getId());
                    if (!resultTramiteCambioestado.isEmpty()) {
                        resultTramiteRegistrado.get().get(i).setTramitesCambioEstados(resultTramiteCambioestado.get());
                        List<TramiteCambioEstadoDTO> cambioEstadoDTOs = new ArrayList<>();
                        cambioEstadoDTOs = resultTramiteRegistrado.get().get(i).getTramitesCambioEstados();
                        if (cambioEstadoDTOs.size() > 1) {
                            TramiteCambioEstadoDTO actualCambioEsatdo = cambioEstadoDTOs.stream().max(Comparator.comparing(x -> x.getFechaRegistro())).get();
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual(actualCambioEsatdo);
                        } else if (cambioEstadoDTOs.size() == 1) {
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual(resultTramiteRegistrado.get().get(i).getTramitesCambioEstados().get(0));
                        }
                    }
                }
                resultTramiteRegistrado = Optional.ofNullable(resultTramiteRegistrado.get().stream().filter(x -> x.getCambioEstadoActual().getTramiteEstado().getNombre().equals(estado)).collect(Collectors.toList()));
            }
            return new ResponseEntity<>(resultTramiteRegistrado, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha/{fecha}")
    @ApiOperation(value = "Obtiene una lista de todos los tramites registrados por cedula", response = TramiteRegistradoDTO.class, responseContainer = "List", tags = "Tramites_Registrados")
    @PreAuthorize("hasAuthority('TRAMITE_CONSULTAR')")
    public @ResponseBody
    ResponseEntity<?> findByFechaTramite(@PathVariable(value = "fecha") String fecha) {
        try {
            Optional<List<TramiteRegistradoDTO>> resultTramiteRegistrado = tramiteRegistradoService.findAll();
            Optional<List<TramiteRegistradoDTO>> resultTramite = null;
            if (resultTramiteRegistrado.isPresent()) {
//                System.out.println(resultTramiteRegistrado.get().size());
                for (int i = 0; i < resultTramiteRegistrado.get().size(); i++) {
                    Optional<List<TramiteCambioEstadoDTO>> resultTramiteCambioestado = tramiteCambioEstadoService.findByTramiteRegistradId(resultTramiteRegistrado.get().get(i).getId());
                    if (!resultTramiteCambioestado.isEmpty()) {
                        resultTramiteRegistrado.get().get(i).setTramitesCambioEstados(resultTramiteCambioestado.get());
                        List<TramiteCambioEstadoDTO> cambioEstadoDTOs = new ArrayList<>();
                        cambioEstadoDTOs = resultTramiteRegistrado.get().get(i).getTramitesCambioEstados();
                        if (cambioEstadoDTOs.size() > 1) {
                            TramiteCambioEstadoDTO actualCambioEsatdo = cambioEstadoDTOs.stream().max(Comparator.comparing(x -> x.getFechaRegistro())).get();
                            System.out.println(actualCambioEsatdo.getFechaRegistro());
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual(actualCambioEsatdo);
                        } else if (cambioEstadoDTOs.size() == 1) {
                            resultTramiteRegistrado.get().get(i).setCambioEstadoActual(resultTramiteRegistrado.get().get(i).getTramitesCambioEstados().get(0));
                        }
                    }
                }
                Date fechafilter = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                
                resultTramite = Optional.ofNullable(resultTramiteRegistrado.get().stream().filter(x -> x.getCambioEstadoActual().getFechaRegistro().getDay() == fechafilter.getDay() && x.getCambioEstadoActual().getFechaRegistro().getMonth() == fechafilter.getMonth() && x.getCambioEstadoActual().getFechaRegistro().getYear() == fechafilter.getYear()).collect(Collectors.toList()));
            }
            return new ResponseEntity<>(resultTramite, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
