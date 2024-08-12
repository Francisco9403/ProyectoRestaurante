package com.project.restaurant.servicio;

import com.project.restaurant.modelo.Menu;
import com.project.restaurant.repositorio.MenuRepository;
import com.project.restaurant.servicio.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Menu> obtenerTodos(int page, int size, String nombre) {
        Pageable pageable = PageRequest.of(page, size);
        return menuRepository.findByNombreContaining(nombre, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Menu obtenerPorId(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Menu CrearMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    @Transactional
    public void eliminarMenu(Long id) {
        menuRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long contarMenu() {
        return menuRepository.count();
    }
}
