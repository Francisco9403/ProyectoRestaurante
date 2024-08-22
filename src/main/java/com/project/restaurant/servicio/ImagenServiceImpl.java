package com.project.restaurant.servicio;

import com.project.restaurant.modelo.Imagen;
import com.project.restaurant.repositorio.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenServiceImpl implements ImagenService {

    private final ImagenRepository imagenRepository;

    @Autowired
    public ImagenServiceImpl(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    @Override
    public Imagen save(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Imagen> findById(Long id) {
        return imagenRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Imagen> findAll() {
        return imagenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Imagen> findByMenuId(Long menuId) {
        return imagenRepository.findByMenuId(menuId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Imagen> findByOfertaId(Long ofertaId) {
        return imagenRepository.findByOfertaId(ofertaId);
    }

    @Override
    public void deleteById(Long id) {
        imagenRepository.deleteById(id);
    }

    @Override
    public void deleteByMenuId(Long menuId) {
        imagenRepository.deleteByMenuId(menuId);
    }

    @Override
    public void deleteByOfertaId(Long ofertaId) {
        imagenRepository.deleteByOfertaId(ofertaId);
    }
}
