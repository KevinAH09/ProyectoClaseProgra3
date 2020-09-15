/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.dto;

import java.util.Date;
import javax.websocket.Decoder.Text;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author colo7
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequisitoDTO {
    private Long id;
    private String descripcion;
    private boolean estado;
    private Date fechaRegistro;
    private VariacionDTO variacion;

//    private List<Usuario> usuarios = new ArrayList<>();
    
    
}
