package com.project.restaurant.servicio;


import com.project.restaurant.modelo.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MenuService {

    Page<Menu> obtenerTodos(int page, int size, String nombre, Double precioMin, Double precioMax);

    Optional<Menu> obtenerPorId(Long id);

    Menu CrearMenu(Menu menu);

    void eliminarMenu(Long id);

    long contarMenu();

}
