package com.AstridAyelen.EjemploSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Producto extends Base {

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    @Min(value = 0, message = "Error interno: El stock resultante no puede ser negativo")
    private int stock;

    @Column(length = 25)
    private String imagen;

    @Column(nullable = false)
    private Boolean disponible;
}
