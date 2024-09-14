package com.project.restaurant.menu.controlador;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.restaurant.imagen.modelo.Imagen;
import com.project.restaurant.menu.modelo.Menu;
import com.project.restaurant.imagen.servicio.ImagenService;
import com.project.restaurant.menu.servicio.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final MenuService menuService;
    private final ImagenService imagenService;

    @Autowired
    public MenuController(MenuService menuService, ImagenService imagenService) {
        this.menuService = menuService;
        this.imagenService = imagenService;
    }

    @GetMapping
    public ResponseEntity<Page<Menu>> obtenerMenus(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "1") int size,
                                                   @RequestParam(defaultValue = "") String nombre,
                                                   @RequestParam(defaultValue = "0") Double precioMin,
                                                   @RequestParam(defaultValue = "999999999") Double precioMax) {

        Page<Menu> menus = menuService.obtenerTodos(page, size, nombre, precioMin, precioMax);
        try {
            // Convertir el objeto Page<Menu> a JSON para inspección
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(menus);

            // Imprimir la respuesta JSON en la consola
            System.out.println("Todos los menus (JSON): " + jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    public ResponseEntity<?> crearMenu(@Valid @RequestPart("menu") Menu menu,
                                       @RequestPart("imagen") MultipartFile imagen,
                                       BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Menu nuevoMenu = new Menu();


        if (!imagen.isEmpty()) {
            Imagen imagenParaGuardar = manejarImagen(imagen);
            nuevoMenu.setImagen(imagenParaGuardar);
        }

        // Guardar el menú en la base de datos primero
        nuevoMenu.setNombre(menu.getNombre());
        nuevoMenu.setDescripcion(menu.getDescripcion());
        nuevoMenu.setPrecio(menu.getPrecio());

        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.CrearMenu(nuevoMenu));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestPart("menu") Menu menu,
                                    @RequestPart(value = "imagen", required = false) MultipartFile imagen,
                                    BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Menu> optionalMenu = menuService.obtenerPorId(id);
        if (optionalMenu.isPresent()) {
            Menu menuDb = optionalMenu.get();

            // Solo reemplaza la imagen si se ha enviado una nueva
            if (imagen != null && !imagen.isEmpty()) {
                Imagen imagenParaGuardar = manejarImagen(imagen);
                menuDb.setImagen(imagenParaGuardar);
            }

            // Actualiza los campos del menú
            menuDb.setNombre(menu.getNombre());
            menuDb.setDescripcion(menu.getDescripcion());
            menuDb.setPrecio(menu.getPrecio());

            // Guarda el menú actualizado en la base de datos
            menuService.CrearMenu(menuDb);
            return ResponseEntity.status(HttpStatus.CREATED).body(menuDb);
        }

        return ResponseEntity.notFound().build();
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

    // Método auxiliar para manejar la carga de imágenes
    private Imagen manejarImagen(MultipartFile archivo) {
        try {
            Imagen imagen = new Imagen();
            imagen.setFile(archivo.getBytes());
            imagen.setFileContentType(archivo.getContentType());

            return imagenService.save(imagen); // Guardar la imagen y retornarla
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen", e);
        }
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err ->
                errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}
