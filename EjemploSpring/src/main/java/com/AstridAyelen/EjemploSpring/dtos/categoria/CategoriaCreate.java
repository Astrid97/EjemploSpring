package com.AstridAyelen.EjemploSpring.dtos.categoria;


import com.AstridAyelen.EjemploSpring.entity.Categoria;

public record CategoriaCreate (String nombre, String descripcion) {
    public Categoria toEntity() {
        return Categoria.builder()
                .nombre(this.nombre())
                .descripcion(this.descripcion())
                .build();
    }
}
