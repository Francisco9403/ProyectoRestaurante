package com.Proyect.restaurant.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;


@Table
public class Oferta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotBlank
    private Double porcentajeDescuento;
    @ManyToOne
    private Menu menu;
    @NotBlank
    private List<Menu> menus;
    @NotBlank
    private String imagen;


    public Oferta(Long id, String nombre, String descripcion, Double porcentajeDescuento, Menu menu, List<Menu> menus, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.porcentajeDescuento = porcentajeDescuento;
        this.menu = menu;
        this.menus = menus;
        this.imagen = imagen;
    }

    public Oferta() {

    }

    public Oferta(String nombre, String descripcion, Double porcentajeDescuento, Menu menu, List<Menu> menus, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.porcentajeDescuento = porcentajeDescuento;
        this.menu = menu;
        this.menus = menus;
        this.imagen = imagen;
    }


    public @NotBlank String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotBlank Double getPrecio() {
        return porcentajeDescuento;
    }

    public void setPrecio(@NotBlank Double porcentaje) {
        this.porcentajeDescuento = porcentaje;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public @NotBlank List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(@NotBlank List<Menu> menus) {
        this.menus = menus;
    }

    public @NotBlank String getImagen() {
        return imagen;
    }

    public void setImagen(@NotBlank String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Ofertas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + porcentajeDescuento +
                ", menu=" + menu +
                ", menus=" + menus +
                ", imagen='" + imagen + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oferta ofertas = (Oferta) o;
        return Objects.equals(nombre, ofertas.nombre) && Objects.equals(descripcion, ofertas.descripcion) && Objects.equals(porcentajeDescuento, ofertas.porcentajeDescuento) && Objects.equals(menu, ofertas.menu) && Objects.equals(menus, ofertas.menus) && Objects.equals(imagen, ofertas.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, porcentajeDescuento, menu, menus, imagen);
    }


}
