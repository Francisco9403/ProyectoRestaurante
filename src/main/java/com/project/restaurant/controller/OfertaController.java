package com.project.restaurant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.restaurant.modelo.Menu;
import com.project.restaurant.servicio.OfertaService;
import com.project.restaurant.modelo.Oferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    private final OfertaService ofertaService;

    @Autowired
    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @GetMapping
    public ResponseEntity<Page<Oferta>> obtenerMenus(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "1") int size,
                                                   @RequestParam(defaultValue = "") String nombre,
                                                   @RequestParam(defaultValue = "0") Double descuentoMin,
                                                   @RequestParam(defaultValue = "999999999") Double descuentoMax) {

        Page<Oferta> ofertas = ofertaService.obtenerTodos(page, size, nombre, descuentoMin, descuentoMax);
        try {
            // Convertir el objeto Page<Menu> a JSON para inspección
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(ofertas);

            // Imprimir la respuesta JSON en la consola
            System.out.println("Todos las ofertas (JSON): " + jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(ofertas);
    }

    // Obtener un menú por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Oferta> obtenerPorId(@PathVariable Long id) {
        Oferta oferta = ofertaService.obtenerPorId(id);
        if (oferta != null) {
            return new ResponseEntity<>(oferta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo menú
    @PostMapping
    public ResponseEntity<Oferta> crearOferta(@RequestBody Oferta oferta) {
        Oferta nuevaOferta = ofertaService.CrearOferta(oferta);
        return new ResponseEntity<>(nuevaOferta, HttpStatus.CREATED);
    }

    // Eliminar un menú por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOferta(@PathVariable Long id) {
        if (ofertaService.obtenerPorId(id) != null) {
            ofertaService.eliminarOferta(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Contar todos los menús
    @GetMapping("/count")
    public ResponseEntity<Long> contarOferta() {
        long count = ofertaService.contarOferta();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
