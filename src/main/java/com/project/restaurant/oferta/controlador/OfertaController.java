package com.project.restaurant.oferta.controlador;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.restaurant.imagen.modelo.Imagen;
import com.project.restaurant.imagen.servicio.ImagenService;
import com.project.restaurant.menu.modelo.Menu;
import com.project.restaurant.menu.servicio.MenuService;
import com.project.restaurant.oferta.servicio.OfertaService;
import com.project.restaurant.oferta.modelo.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    private final OfertaService ofertaService;
    private final MenuService menuService;
    private final ImagenService imagenService;

    @Autowired
    public OfertaController(OfertaService ofertaService, MenuService menuService, ImagenService imagenService) {
        this.ofertaService = ofertaService;
        this.menuService = menuService;
        this.imagenService = imagenService;
    }

    @GetMapping()
    public ResponseEntity<Page<Oferta>> obtenerOfertas(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "1") int size,
                                                       @RequestParam(defaultValue = "") String nombre,
                                                       @RequestParam(defaultValue = "0") Double ofertaMin,
                                                       @RequestParam(defaultValue = "999999999") Double ofertaMax) {

        Page<Oferta> ofertas = ofertaService.obtenerTodos(page, size, nombre, ofertaMin, ofertaMax);
        try {
            // Convertir el objeto Page<Menu> a JSON para inspección
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(ofertas);

            // Imprimir la respuesta JSON en la consola
            System.out.println("Todos los menus (JSON): " + jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(ofertas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> obtenerPorId(@PathVariable Long id) {
        Oferta oferta = ofertaService.obtenerPorId(id);
        if (oferta != null) {
            return new ResponseEntity<>(oferta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping
//    public ResponseEntity<Oferta> crearOferta(@RequestBody Oferta oferta) {
//        Oferta nuevaOferta = ofertaService.CrearOferta(oferta);
//        return new ResponseEntity<>(nuevaOferta, HttpStatus.CREATED);
//    }

    //metodo agregado
//    @PostMapping
//    public ResponseEntity<Oferta> createOffer(
//            @RequestPart("ofertaJson") String ofertaJson,
//            @RequestPart(value = "imagen") MultipartFile imagenFile,
//            @RequestPart(value = "menuIds") String menuIdsJson) throws IOException {
//
//        // Convertir ofertaJson a un objeto Oferta
//        Oferta nuevaOferta = new ObjectMapper().readValue(ofertaJson, Oferta.class);
//
//        // Convertir el JSON de menuIds a una lista de IDs
//        List<Long> menuIds = new ObjectMapper().readValue(menuIdsJson, new TypeReference<List<Long>>() {});
//
//        // Verificar si alguno de los menús seleccionados ya está asociado a una oferta
//        for (Long menuId : menuIds) {
//            Optional<Menu> menu = menuService.obtenerPorId(menuId);
//            if (menu.isPresent() && menu.get().getOferta() != null) {
//                Oferta ofertaExistente = menu.get().getOferta();
//
//                // Eliminar la oferta existente
//                ofertaService.eliminarOferta(ofertaExistente.getId());
//
//                // Remover la asociación de todos los menús con esta oferta existente
//                List<Menu> menusAsociados = menuService.obtenerMenusPorOferta(ofertaExistente.getId());
//                for (Menu m : menusAsociados) {
//                    m.setOferta(null);
//                    menuService.CrearMenu(m);
//                }
//            }
//        }
//
//        // Manejar la imagen si está presente
//        if (imagenFile != null && !imagenFile.isEmpty()) {
//            Imagen imagen = manejarImagen(imagenFile);
//            nuevaOferta.setImagen(imagen);
//        }
//
//        // Crear la nueva oferta
//        Oferta ofertaCreada = ofertaService.CrearOferta(nuevaOferta);
//
//        // Asociar los menús con la nueva oferta
//        if (!menuIds.isEmpty()) {
//            for (Long menuId : menuIds) {
//                Optional<Menu> menu = menuService.obtenerPorId(menuId);
//                menu.ifPresent(m -> {
//                    m.setOferta(ofertaCreada);
//                    menuService.CrearMenu(m);
//                });
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaCreada);
//    }
    @PostMapping
    public ResponseEntity<Oferta> createOffer(
            @RequestPart("ofertaJson") String ofertaJson,
            @RequestPart(value = "imagen") MultipartFile imagenFile,
            @RequestPart(value = "menuIds") String menuIdsJson) throws IOException {

        // Convertir ofertaJson a un objeto Oferta
        Oferta nuevaOferta = new ObjectMapper().readValue(ofertaJson, Oferta.class);

        // Convertir el JSON de menuIds a una lista de IDs
        List<Long> menuIds = new ObjectMapper().readValue(menuIdsJson, new TypeReference<List<Long>>() {});

        // Verificar si alguno de los menús seleccionados ya está asociado a una oferta
        for (Long menuId : menuIds) {
            Optional<Menu> menu = menuService.obtenerPorId(menuId);
            if (menu.isPresent() && menu.get().getOferta() != null) {
                Oferta ofertaExistente = menu.get().getOferta();

                // Remover la asociación de todos los menús con esta oferta existente
                List<Menu> menusAsociados = menuService.obtenerMenusPorOferta(ofertaExistente.getId());
                for (Menu m : menusAsociados) {
                    m.setOferta(null);
                    menuService.CrearMenu(m);
                }

                // Eliminar la oferta existente
                ofertaService.eliminarOferta(ofertaExistente.getId());
            }
        }

        // Manejar la imagen si está presente
        if (imagenFile != null && !imagenFile.isEmpty()) {
            Imagen imagen = manejarImagen(imagenFile);
            nuevaOferta.setImagen(imagen);
        }

        // Crear la nueva oferta
        Oferta ofertaCreada = ofertaService.CrearOferta(nuevaOferta);

        // Asociar los menús con la nueva oferta
        if (!menuIds.isEmpty()) {
            for (Long menuId : menuIds) {
                Optional<Menu> menu = menuService.obtenerPorId(menuId);
                menu.ifPresent(m -> {
                    m.setOferta(ofertaCreada);
                    menuService.CrearMenu(m);
                });
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(ofertaCreada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOferta(@PathVariable Long id) {
        Oferta oferta = ofertaService.obtenerPorId(id);

        if (oferta != null) {

            System.out.println("test: " + oferta.getNombre());
            List<Menu> menusAsociados = menuService.obtenerMenusPorOferta(id);
            for (Menu menu : menusAsociados) {
                menu.setOferta(null);
                menuService.CrearMenu(menu);
            }
            ofertaService.eliminarOferta(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarOferta() {
        long count = ofertaService.contarOferta();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

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
}
