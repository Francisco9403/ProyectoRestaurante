package com.project.restaurant.oferta.servicio;


import com.project.restaurant.oferta.modelo.Oferta;
import org.springframework.data.domain.Page;

public interface OfertaService {

    Page<Oferta> obtenerTodos(int page, int size, String nombre, Double ofertaMin, Double ofertaMax);

    Oferta obtenerPorId(Long id);

    Oferta obtenerPorNombre(String nombre);

    Oferta CrearOferta(Oferta oferta);

    void eliminarOferta(Long id);

    long contarOferta();

}
