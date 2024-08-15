package com.project.restaurant.controller;

import com.project.restaurant.modelo.Menu;
import com.project.restaurant.servicio.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
                                                   @RequestParam(defaultValue = "") String nombre,
                                                   @RequestParam(defaultValue = "0") Double precioMin,
                                                   @RequestParam(defaultValue = "10000") Double precioMax) {

        precioMax = Double.MAX_VALUE;

        Page<Menu> menus = menuService.obtenerTodos(page, size, nombre, precioMin, precioMax);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Menu> o = menuService.obtenerPorId(id);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crearMenu(@Valid @RequestBody Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.CrearMenu(menu));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMenu(@PathVariable Long id) {
        Optional<Menu> o = menuService.obtenerPorId(id);
        if (o.isPresent()) {
            menuService.eliminarMenu(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarMenu() {
        return ResponseEntity.ok(menuService.contarMenu());
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->
                errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}
