package com.project.restaurant.oferta.repositorio;

import com.project.restaurant.oferta.modelo.Oferta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    Page<Oferta> findByNombreContainingAndPorcentajeDescuentoBetween(String nombre, Double porcentajeMin, Double porcentajeMax, Pageable pageable);
    Oferta findByNombre(String nombre);
}
