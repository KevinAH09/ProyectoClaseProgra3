/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author Bosco
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest hsr, HttpServletResponse hsr1, org.springframework.security.access.AccessDeniedException ade) throws IOException, ServletException {
        hsr1.setContentType(MediaType.APPLICATION_JSON_VALUE);
        hsr1.setStatus(HttpServletResponse.SC_FORBIDDEN);
        try {
            hsr1.getWriter().write(new JSONObject()
                    .put("timestamp", LocalDateTime.now())
                    .put("mensaje", "Se requiere un permiso adicional para realizar esta accion")
                    .toString());
        } catch (JSONException ex) {
            Logger.getLogger(CustomAccessDeniedHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
