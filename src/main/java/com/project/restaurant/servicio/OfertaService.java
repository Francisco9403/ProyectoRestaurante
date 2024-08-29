package com.project.restaurant.servicio;


import com.project.restaurant.modelo.Oferta;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OfertaService {

    Page<Oferta> obtenerTodos(int page, int size, String nombre, Double descuentoMin, Double descuentoMax);

    Oferta obtenerPorId(Long id);

    Oferta CrearOferta(Oferta oferta);

    void eliminarOferta(Long id);

    long contarOferta();

}
