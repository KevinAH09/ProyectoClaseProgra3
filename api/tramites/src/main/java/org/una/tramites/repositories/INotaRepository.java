/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.Nota;

/**
 *
 * @author colo7
 */
public interface INotaRepository extends JpaRepository<Nota, Long>{
    
    public Nota findByTitulo(String titulo);
}
