package com.Proyect.restaurant.servicio;

import com.Proyect.restaurant.modelo.Menu;
import com.Proyect.restaurant.modelo.Oferta;
import com.Proyect.restaurant.repositorio.MenuRepository;
import com.Proyect.restaurant.repositorio.OfertasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OfertaServiceImpl implements OfertaService{

    private final OfertasRepository ofertasRepository;


    @Autowired
    public OfertaServiceImpl(OfertasRepository ofertasRepository) {
        this.ofertasRepository = ofertasRepository;
    }

    @Override
    public List<Oferta> obtenerTodos() {
        return ofertasRepository.findAll();
    }

    @Override
    public Oferta obtenerPorId(Long id) {
        return ofertasRepository.findById(id).orElse(null);
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
    public long contarOferta() {
        return ofertasRepository.count();
    }

}
