package com.AstridAyelen.EjemploSpring.dtos.producto;

import com.AstridAyelen.EjemploSpring.entity.Producto;
import jakarta.validation.constraints.Min;

public record ProductoEdit(

        @Min(value = 1, message = "El precio debe ser mayor a 0")
        Double precio,

        @Min(value =  0, message = "El stock no puede ser menor a cero")
        int stock,

        Boolean disponible
) {
    public void applyTo(Producto producto) {
        if (this.precio != null) {
            producto.setPrecio(this.precio);
        }
        if (this.disponible != null) {
            producto.setDisponible(this.disponible);
        }
    }
}