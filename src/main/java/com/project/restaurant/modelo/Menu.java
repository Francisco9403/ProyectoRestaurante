package com.project.restaurant.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "Descripción no puede estar vacío")
    private String descripcion;

    @NotNull(message = "Precio no puede estar vacío")
    private Double precio;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;

//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
//    private List<Oferta> ofertas;

    // Constructores, getters, setters, equals, hashCode, toString
    public Menu(Long id, String nombre, String descripcion, Double precio, Imagen imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Nombre no puede estar vacío") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "Nombre no puede estar vacío") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "Descripción no puede estar vacío") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank(message = "Descripción no puede estar vacío") String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotNull(message = "Precio no puede estar vacío") Double getPrecio() {
        return precio;
    }

    public void setPrecio(@NotNull(message = "Precio no puede estar vacío") Double precio) {
        this.precio = precio;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

//    public List<Oferta> getOfertas() {
//        return ofertas;
//    }
//
//    public void setOfertas(List<Oferta> ofertas) {
//        this.ofertas = ofertas;
//    }

    @Override
    public String toString() {
        return "Menu{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(nombre, menu.nombre) && Objects.equals(descripcion, menu.descripcion) && Objects.equals(precio, menu.precio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, precio);
    }
}
