package com.project.restaurant.servicio;

import com.project.restaurant.modelo.Imagen;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ImagenService {

    Imagen save(Imagen imagen);

    Optional<Imagen> findById(Long id);

    List<Imagen> findAll();

//    List<Imagen> findByMenuId(Long menuId);
//
//    List<Imagen> findByOfertaId(Long ofertaId);

    void deleteById(Long id);
//
//    void deleteByMenuId(Long menuId);
//
//    void deleteByOfertaId(Long ofertaId);
}
