package com.proyect.restaurant.servicio;

import com.proyect.restaurant.modelo.Oferta;
import com.proyect.restaurant.repositorio.OfertasRepository;
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
    @Transactional(readOnly = true)
    public List<Oferta> obtenerTodos() {
        return ofertasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public long contarOferta() {
        return ofertasRepository.count();
    }

}
