package com.proyect.restaurant.servicio;


import com.proyect.restaurant.modelo.Oferta;

import java.util.List;

public interface OfertaService {

    List<Oferta> obtenerTodos();

    Oferta obtenerPorId(Long id);

    Oferta CrearOferta(Oferta oferta);

    void eliminarOferta(Long id);

    long contarOferta();

}
