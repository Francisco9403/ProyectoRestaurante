package com.Proyect.restaurant.repositorio;

import com.Proyect.restaurant.modelo.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertasRepository extends JpaRepository<Oferta, Long> {

}
