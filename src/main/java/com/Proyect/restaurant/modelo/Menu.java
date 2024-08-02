package com.Proyect.restaurant.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;


@Table
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotBlank
    private Double precio;
    @Transient
    private String imagen;

    public Menu(Long id, String nombre, String descripcion, String imagen, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
    }

    public Menu() {
    }

    public Menu(String nombre, String descripcion, String imagen, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
    }

    public @NotBlank Double getPrecio() {
        return precio;
    }

    public void setPrecio(@NotBlank Double precio) {
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "menu{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", imagen='" + imagen + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(nombre, menu.nombre) && Objects.equals(descripcion, menu.descripcion) && Objects.equals(precio, menu.precio) && Objects.equals(imagen, menu.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, precio, imagen);
    }


}
