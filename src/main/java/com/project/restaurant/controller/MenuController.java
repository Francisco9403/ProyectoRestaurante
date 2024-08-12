package com.project.restaurant.controller;

import com.project.restaurant.modelo.Menu;
import com.project.restaurant.servicio.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<Page<Menu>> obtenerMenus(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "1") int size,
                                   @RequestParam(defaultValue = "") String nombre) {

        Page<Menu> menus = menuService.obtenerTodos(page, size, nombre);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtenerPorId(@PathVariable Long id) {
        Menu menu = menuService.obtenerPorId(id);
        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Menu> crearMenu(@RequestBody Menu menu) {
        Menu nuevoMenu = menuService.CrearMenu(menu);
        return new ResponseEntity<>(nuevoMenu, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMenu(@PathVariable Long id) {
        if (menuService.obtenerPorId(id) != null) {
            menuService.eliminarMenu(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarMenu() {
        long count = menuService.contarMenu();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
