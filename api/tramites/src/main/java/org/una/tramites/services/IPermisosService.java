/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.tramites.entities.Permisos;

/**
 *
 * @author cfugu
 */
public interface IPermisosService {

    public Optional<List<Permisos>> findAll();

    public Optional<Permisos> findById(Long id);
//    public Optional<Permisos> findByCodigo(String codigo);

    public Permisos create(Permisos permiso);

    public Optional<Permisos> update(Permisos permiso, Long id);

    public Optional<List<Permisos>> findByEstado(boolean estado);

    public Optional<List<Permisos>> findByFechaRegistroBetween(Date startDate, Date endDate);//Crear query

    public void delete(Long id);

    public void deleteAll();

}
