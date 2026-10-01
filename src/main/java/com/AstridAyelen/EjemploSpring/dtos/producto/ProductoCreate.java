package com.AstridAyelen.EjemploSpring.dtos.producto;

import com.AstridAyelen.EjemploSpring.entity.Producto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoCreate(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @Min(value = 1, message = "El precio debe ser mayor a 0")
        Double precio,

        String descripcion,

        @Min(value = 0, message = "El stock no puede ser menor a cero")
        int stock,

        String imagen,

        @NotNull(message = "Debe indicar obligatoriamente si esta disponible (true o false)")
        Boolean disponible
) {
    public Producto toEntity() {
        return Producto.builder()
                .nombre(this.nombre())
                .precio(this.precio())
                .descripcion(this.descripcion())
                .stock(this.stock())
                .imagen(this.imagen())
                .disponible(this.disponible())
                .build();
    }
}