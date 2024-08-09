package com.project.restaurant.servicio;


import com.project.restaurant.modelo.Oferta;

import java.util.List;

public interface OfertaService {

    List<Oferta> obtenerTodos();

    Oferta obtenerPorId(Long id);

    Oferta CrearOferta(Oferta oferta);

    void eliminarOferta(Long id);

    long contarOferta();

}
