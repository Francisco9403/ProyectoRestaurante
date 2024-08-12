package com.project.restaurant.repositorio;

import com.project.restaurant.modelo.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Page<Menu> findByNombreContaining(String nombre, Pageable pageable);
}
