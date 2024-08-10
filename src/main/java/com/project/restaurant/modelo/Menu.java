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

    @NotBlank(message = "Imagen no puede estar vacío")
    private String imagen;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Oferta> ofertas;

    // Constructores, getters, setters, equals, hashCode, toString

    public Menu(Long id, String nombre, String descripcion, Double precio, String imagen, List<Oferta> ofertas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.ofertas = ofertas;
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

    public @NotBlank(message = "Imagen no puede estar vacío") String getImagen() {
        return imagen;
    }

    public void setImagen(@NotBlank(message = "Imagen no puede estar vacío") String imagen) {
        this.imagen = imagen;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", imagen='" + imagen + '\'' +
                ", ofertas=" + ofertas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(nombre, menu.nombre) && Objects.equals(descripcion, menu.descripcion) && Objects.equals(precio, menu.precio) && Objects.equals(imagen, menu.imagen) && Objects.equals(ofertas, menu.ofertas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, precio, imagen, ofertas);
    }
}
