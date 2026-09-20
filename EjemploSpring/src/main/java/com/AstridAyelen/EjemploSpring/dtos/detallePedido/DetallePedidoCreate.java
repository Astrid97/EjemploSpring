package com.AstridAyelen.EjemploSpring.dtos.detallePedido;

public record DetallePedidoCreate(
        int cantidad,
        Long idProducto
) {
    // No lleva toEntity() porque para armar el detalle real necesitamos
    // ir a la base de datos a buscar el Producto y calcular el subtotal.
}