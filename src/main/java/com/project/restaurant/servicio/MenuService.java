package com.project.restaurant.servicio;


import com.project.restaurant.modelo.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> obtenerTodos();

    Menu obtenerPorId(Long id);

    Menu CrearMenu(Menu menu);

    void eliminarMenu(Long id);

    long contarMenu();

}
