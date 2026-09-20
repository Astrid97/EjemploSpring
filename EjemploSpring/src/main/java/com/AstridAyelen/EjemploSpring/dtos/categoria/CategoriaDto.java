package com.AstridAyelen.EjemploSpring.dtos.categoria;


import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoDto;
import com.AstridAyelen.EjemploSpring.entity.Categoria;

import java.util.List;

public record CategoriaDto(
        Long id,
        String nombre,
        String descripcion,
        List<ProductoDto> productos
) {
    public static CategoriaDto toDto(Categoria categoria) {
        return new CategoriaDto(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getProductos() == null ? List.of() :
                        categoria.getProductos().stream()
                                .map(ProductoDto::toDto)
                                .toList()
        );
    }
}
