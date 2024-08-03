package com.Proyect.restaurant.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;


@Table(name = "oferta")
public class Oferta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "Descripción no puede estar vacío")
    private String descripcion;

    @NotBlank(message = "Porcentaje no puede estar vacío")
    private Double porcentajeDescuento;

    @OneToMany(mappedBy="oferta")
    private List<Menu> menu;

    @NotBlank(message = "Imagen no puede estar vacío")
    private String imagen;

    public Oferta(Long id, String nombre, String descripcion, Double porcentajeDescuento, List<Menu> menu, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.porcentajeDescuento = porcentajeDescuento;
        this.menu = menu;
        this.imagen = imagen;
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

    public @NotBlank(message = "Porcentaje no puede estar vacío") Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(@NotBlank(message = "Porcentaje no puede estar vacío") Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public @NotBlank(message = "Imagen no puede estar vacío") String getImagen() {
        return imagen;
    }

    public void setImagen(@NotBlank(message = "Imagen no puede estar vacío") String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Oferta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", porcentajeDescuento=" + porcentajeDescuento +
                ", menu=" + menu +
                ", imagen='" + imagen + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oferta oferta = (Oferta) o;
        return Objects.equals(nombre, oferta.nombre) && Objects.equals(descripcion, oferta.descripcion) && Objects.equals(porcentajeDescuento, oferta.porcentajeDescuento) && Objects.equals(menu, oferta.menu) && Objects.equals(imagen, oferta.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, porcentajeDescuento, menu, imagen);
    }
}
