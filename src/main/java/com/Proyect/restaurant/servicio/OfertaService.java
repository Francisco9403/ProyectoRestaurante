package com.Proyect.restaurant.servicio;


import com.Proyect.restaurant.modelo.Oferta;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OfertaService {

    List<Oferta> obtenerTodos();

    Oferta obtenerPorId(Long id);

    Oferta CrearOferta(Oferta oferta);

    void eliminarOferta(Long id);

    long contarOferta();

}
