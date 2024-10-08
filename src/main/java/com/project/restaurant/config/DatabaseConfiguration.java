package com.project.restaurant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({
        "com.project.restaurant.imagen.repositorio",
        "com.project.restaurant.menu.repositorio",
        "com.project.restaurant.oferta.repositorio",
        "com.project.restaurant.user.repositorio"// Habilita la detección de repositorios JPA en el paquete especificado.
})
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@EnableTransactionManagement
@EnableJpaAuditing
public class DatabaseConfiguration {
}
