package com.AstridAyelen.EjemploSpring.dtos.pedido;

import com.AstridAyelen.EjemploSpring.dtos.detallePedido.DetallePedidoDto;
import com.AstridAyelen.EjemploSpring.entity.Pedido;
import com.AstridAyelen.EjemploSpring.enums.Estado;
import com.AstridAyelen.EjemploSpring.enums.FormaPago;

import java.time.LocalDate;
import java.util.List;

public record PedidoDto(
        Long id,
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago,
        List<DetallePedidoDto> detalles
) {
    public static PedidoDto toDto(Pedido pedido) {
        return new PedidoDto(
                pedido.getId(),
                pedido.getFecha(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getFormaPago(),
                // Recorremos los detalles y los convertimos a DTO
                pedido.getDetalles().stream()
                        .map(DetallePedidoDto::toDto)
                        .toList()
        );
    }
}