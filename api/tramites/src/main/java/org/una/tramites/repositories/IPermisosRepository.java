/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.tramites.entities.Permisos;

/**
 *
 * @author cfugu
 */
public interface IPermisosRepository extends JpaRepository<Permisos, Long> {

    public List<Permisos> findByEstadoContaining(boolean estado);

    public List<Permisos> findByCodigoIgnoreCase(String codigo);
}
