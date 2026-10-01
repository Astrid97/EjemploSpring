package com.AstridAyelen.EjemploSpring.dtos.detallePedido;

import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoDto;
import com.AstridAyelen.EjemploSpring.entity.DetallePedido;

public record DetallePedidoDto(
        Long id,
        int cantidad,
        Double subtotal,
        ProductoDto producto
) {
    public static DetallePedidoDto toDto(DetallePedido detalle) {
        return new DetallePedidoDto(
                detalle.getId(),
                detalle.getCantidad(),
                detalle.getSubtotal(),
                ProductoDto.toDto(detalle.getProducto()) // Convertimos la Entidad a DTO
        );
    }
}