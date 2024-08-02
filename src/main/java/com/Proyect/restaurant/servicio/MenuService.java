package com.Proyect.restaurant.servicio;


import com.Proyect.restaurant.modelo.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

    List<Menu> obtenerTodos();

    Menu obtenerPorId(Long id);

    Menu CrearMenu(Menu menu);

    void eliminarMenu(Long id);

    long contarMenu();

}
