package com.AstridAyelen.EjemploSpring.dtos.producto;

import com.AstridAyelen.EjemploSpring.entity.Producto;

public record ProductoDto(
        Long id,
        String nombre,
        Double precio,
        String descripcion,
        int stock,
        String imagen,
        Boolean disponible
) {
    public static ProductoDto toDto(Producto producto) {
        return new ProductoDto(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getDescripcion(),
                producto.getStock(),
                producto.getImagen(),
                producto.getDisponible()
        );
    }
}