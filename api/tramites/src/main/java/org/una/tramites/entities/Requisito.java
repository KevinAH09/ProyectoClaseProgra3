/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.websocket.Decoder.Text;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/**
 *
 * @author colo7
 */
@Entity
@Table(name = "requisitos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Requisito implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, name = "descripcion")
    private String descripcion;

    @Column
    private boolean estado;

    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.DATE)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "variaciones")//creo que es departamotos
//    private List<Usuario> usuarios = new ArrayList<>();
    
    
    
    @PrePersist
    public void prePersist() {
        estado=true;
        fechaRegistro = new Date();
    }

//    @PreUpdate
//    public void preUpdate() {
//       estado=true;
//    }
}
