package com.Proyect.restaurant.servicio;


import com.Proyect.restaurant.modelo.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> obtenerTodos();

    Menu obtenerPorId(Long id);

    Menu CrearMenu(Menu menu);

    void eliminarMenu(Long id);

    long contarMenu();

}
