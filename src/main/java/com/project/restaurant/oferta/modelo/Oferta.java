package com.project.restaurant.oferta.modelo;

import com.project.restaurant.imagen.modelo.Imagen;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "oferta")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "Descripción no puede estar vacío")
    private String descripcion;

    @NotNull(message = "Porcentaje no puede estar vacío")
    private Double porcentajeDescuento;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "oferta_id")  // Asegúrate de que la columna existe en la tabla
//    private List<Menu> menus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "imagen_id")
    private Imagen imagen;

    // Constructores, getters, setters, equals, hashCode, toString
    public Oferta(Long id, String nombre, String descripcion, Double porcentajeDescuento, Imagen imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.porcentajeDescuento = porcentajeDescuento;
        this.imagen = imagen;
    }

    public Oferta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

//    public List<Menu> getMenus() {
//        return menus;
//    }
//
//    public void setMenus(List<Menu> menus) {
//        this.menus = menus;
//    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oferta oferta = (Oferta) o;
        return Objects.equals(nombre, oferta.nombre) && Objects.equals(descripcion, oferta.descripcion) && Objects.equals(porcentajeDescuento, oferta.porcentajeDescuento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, porcentajeDescuento);
    }

    @Override
    public String toString() {
        return "Oferta{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", porcentajeDescuento=" + porcentajeDescuento +
                '}';
    }
}
