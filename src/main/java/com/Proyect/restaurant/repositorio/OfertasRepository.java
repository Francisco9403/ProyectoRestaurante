package com.proyect.restaurant.repositorio;

import com.proyect.restaurant.modelo.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertasRepository extends JpaRepository<Oferta, Long> {

}
