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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Bosco
 */
@Entity
@Table(name = "variaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Variacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 150, name = "Descripcion")
    private String descripcion;
    
    @Column
    private boolean grupo;
    
    @Column
    private boolean estado;

    
    @ManyToOne
    @JoinColumn(name = "tramites_tipos_id")
    private TramiteTipo tramiteTipo;

    private static final long serialVersionUID = 1L;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;

    @PrePersist
    public void prePersist() {
        
        fechaRegistro = new Date();
    }

}
