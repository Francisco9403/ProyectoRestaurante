package com.project.restaurant.menu.servicio;


import com.project.restaurant.menu.modelo.Menu;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    Page<Menu> obtenerTodos(int page, int size, String nombre, Double precioMin, Double precioMax);

    Optional<Menu> obtenerPorId(Long id);

    Menu CrearMenu(Menu menu);

    void eliminarMenu(Long id);

    long contarMenu();

    List<Menu> obtenerMenusPorOferta(Long id);
}
