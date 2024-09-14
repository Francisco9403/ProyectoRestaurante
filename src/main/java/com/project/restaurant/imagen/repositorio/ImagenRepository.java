package com.project.restaurant.imagen.repositorio;

import com.project.restaurant.imagen.modelo.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
////    List<Imagen> findByMenuId(Long menuId);
////
////    List<Imagen> findByOfertaId(Long ofertaId);
//
//    void deleteByMenuId(Long menuId);
//
//    void deleteByOfertaId(Long ofertaId);
}
