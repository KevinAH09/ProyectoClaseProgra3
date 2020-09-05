/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author colo7
 */
@Entity
@Table(name = "tramites_registrados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteRegistrado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="clientes_id")
    private Cliente cliente;
    
    @ManyToOne 
    @JoinColumn(name="tramites_tipos_id")
    private TramiteTipo tramitesTipos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramites_registrados") 
    private List<ArchivoRelacionado> archivoRelacionado = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramites_registrados") 
    private List<Nota> nota = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramites_registrados") 
    private List<RequisitoPresentado> requisitoPresentado = new ArrayList<>();
    
    
    private static final long serialVersionUID = 1L;
    
}
