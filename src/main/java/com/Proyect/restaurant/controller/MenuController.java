package com.proyect.restaurant.controller;

import com.proyect.restaurant.modelo.Menu;
import com.proyect.restaurant.servicio.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Obtener todos los menús
    @GetMapping
    public ResponseEntity<List<Menu>> obtenerTodos() {
        List<Menu> menus = menuService.obtenerTodos();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    // Obtener un menú por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtenerPorId(@PathVariable Long id) {
        Menu menu = menuService.obtenerPorId(id);
        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo menú
    @PostMapping
    public ResponseEntity<Menu> crearMenu(@RequestBody Menu menu) {
        Menu nuevoMenu = menuService.CrearMenu(menu);
        return new ResponseEntity<>(nuevoMenu, HttpStatus.CREATED);
    }

    // Eliminar un menú por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMenu(@PathVariable Long id) {
        if (menuService.obtenerPorId(id) != null) {
            menuService.eliminarMenu(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Contar todos los menús
    @GetMapping("/count")
    public ResponseEntity<Long> contarMenu() {
        long count = menuService.contarMenu();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
