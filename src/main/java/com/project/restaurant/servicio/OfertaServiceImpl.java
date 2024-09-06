package com.project.restaurant.servicio;

import com.project.restaurant.modelo.Menu;
import com.project.restaurant.modelo.Oferta;
import com.project.restaurant.repositorio.OfertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfertaServiceImpl implements OfertaService{

    private final OfertaRepository ofertasRepository;


    @Autowired
    public OfertaServiceImpl(OfertaRepository ofertasRepository) {
        this.ofertasRepository = ofertasRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Oferta> obtenerTodos(int page, int size, String nombre, Double precioMin, Double precioMax) {
        Pageable pageable = PageRequest.of(page, size);

        return ofertasRepository.findByNombreContainingAndPorcentajeDescuentoBetween(nombre, precioMin, precioMax, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Oferta obtenerPorId(Long id) {
        return ofertasRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Oferta obtenerPorNombre(String nombre) {
        return ofertasRepository.findByNombre(nombre);
    }

    @Override
    @Transactional
    public Oferta CrearOferta(Oferta oferta) {
        return ofertasRepository.save(oferta);
    }

    @Override
    @Transactional
    public void eliminarOferta(Long id) {
        ofertasRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long contarOferta() {
        return ofertasRepository.count();
    }

}
