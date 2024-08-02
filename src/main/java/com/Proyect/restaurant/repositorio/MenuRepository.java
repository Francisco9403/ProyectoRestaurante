package com.Proyect.restaurant.repositorio;

import com.Proyect.restaurant.modelo.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

}
