package com.AstridAyelen.EjemploSpring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Categoria extends Base {

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private List<Producto> productos = new ArrayList<>();
}
