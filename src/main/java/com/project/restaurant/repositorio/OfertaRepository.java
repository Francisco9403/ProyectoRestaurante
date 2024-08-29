package com.project.restaurant.repositorio;

import com.project.restaurant.modelo.Menu;
import com.project.restaurant.modelo.Oferta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    Page<Oferta> findByNombreContainingAndPorcentajeDescuentoBetween(String nombre, Double porcentajeMin, Double porcentajeMax, Pageable pageable);
}
