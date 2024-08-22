package com.project.restaurant.repositorio;

import com.project.restaurant.modelo.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
    List<Imagen> findByMenuId(Long menuId);

    List<Imagen> findByOfertaId(Long ofertaId);

    void deleteByMenuId(Long menuId);

    void deleteByOfertaId(Long ofertaId);
}
