///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.una.tramites.services;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.una.tramites.entities.PermisosOtorgados;
//import org.una.tramites.entities.Usuario;
//import org.una.tramites.repositories.IPermisosOtorgadosRepository;
//
///**
// *
// * @author cfugu
// */
//@Service
//public class PermisosOtorgadosServiceImplementation implements IPermisosOtorgadosService{
//
//    @Autowired
//    private IPermisosOtorgadosRepository permisoOtorgadosRepos;
//    
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<PermisosOtorgados> findById(Long usuarioId) {
//        return permisoOtorgadosRepos.findById(usuarioId);
//    }
//
//    

//    @Override
//    public Optional<List<PermisosOtorgados>> findByUsuarioIdAndEstado(Long usuarioId, boolean estado) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Optional<List<PermisosOtorgados>> findByPermisoIdAndEstado(Long permisoId, boolean estado) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
////    @Override
////    public Optional<List<PermisosOtorgados>> findByFechaRegistroBetween(Date startDate, Date endDate) {
////        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
////    }
//
//    @Override
//    @Transactional
//    public PermisosOtorgados create(PermisosOtorgados permisoOtorgado) {
//        return permisoOtorgadosRepos.save(permisoOtorgado);
//    }
//
//    @Override
//    @Transactional
//    public Optional<PermisosOtorgados> update(PermisosOtorgados permisoOtorgado, Long id) {
//         if (permisoOtorgadosRepos.findById(id).isPresent()) {
//            return Optional.ofNullable(permisoOtorgadosRepos.save(permisoOtorgado));
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    @Transactional
//    public void delete(Long id) {
//       permisoOtorgadosRepos.deleteById(id);
//    }
//
//    @Override
//    @Transactional
//    public void deleteAll() {
//        permisoOtorgadosRepos.deleteAll();
//    }
//
//   
//    @Override
//    @Transactional(readOnly = true)
//    public Optional<List<PermisosOtorgados>> findAll() {
//        return Optional.ofNullable(permisoOtorgadosRepos.findAll());
//    }
//
////    @Override
////    @Transactional(readOnly = true)
////    public Optional findByUsuarioId(Long id) {
////        return Optional.ofNullable(permisoOtorgadosRepos.findByUsuarioId(id));
////    }
////
////    @Override
////    @Transactional(readOnly = true)
////    public Optional  findByPermisoId(Long id) {
////        return Optional.ofNullable(permisoOtorgadosRepos.findByUsuarioId(id));
////    }
//    
//}
