/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.tramites.entities.Nota;

/**
 *
 * @author colo7
 */
public interface INotaRepository extends JpaRepository<Nota, Long>{
    
    public Nota findByTitulo(String titulo);
    
    @Query("SELECT u FROM Nota u LEFT JOIN u.tramiteRegistrado d WHERE  d.id=:id")
    public List<Nota> findByRegistroIdImplementado(@Param("id") Long id);
}
