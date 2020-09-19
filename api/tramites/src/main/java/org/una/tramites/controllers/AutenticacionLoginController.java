/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.tramites.dto.AuthenticationRequest;
import org.una.tramites.dto.AuthenticationResponse;
import org.una.tramites.dto.UsuarioDTO;
import org.una.tramites.services.IAutenticacionLoginService;

/**
 *
 * @author Bosco
 */
@RestController
@RequestMapping("/login")
@Api(tags = {"Seguridad"})
public class AutenticacionLoginController {

    @Autowired
    private IAutenticacionLoginService login;

//    @PostMapping("/login")
//    @ResponseBody
//    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
//    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return new ResponseEntity("La información no esta bien formada o no coincide con el formato esperado", HttpStatus.BAD_REQUEST);
//        }
//        try {
//            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//            Optional<AuthenticationResponse> result = Optional.of(login.login(authenticationRequest));
//            
//            if (result.isPresent()) {
//                authenticationResponse =result.get();
//                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = UsuarioDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {

        final String MENSAJE_VERIFICAR_CREDENCIALES = "Debe verificar y proporcionar credenciales correctos para realizar esta acción";
        final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

        if (!bindingResult.hasErrors()) {
            try {
                return new ResponseEntity(login.login(authenticationRequest), HttpStatus.OK);

            } catch (UsernameNotFoundException | BadCredentialsException e) {
                return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.UNAUTHORIZED);

            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
}
