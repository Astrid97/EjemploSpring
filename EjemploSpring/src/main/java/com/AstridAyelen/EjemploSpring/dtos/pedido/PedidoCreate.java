package com.AstridAyelen.EjemploSpring.dtos.pedido;

import com.AstridAyelen.EjemploSpring.dtos.detallePedido.DetallePedidoCreate;
import com.AstridAyelen.EjemploSpring.enums.Estado;
import com.AstridAyelen.EjemploSpring.enums.FormaPago;

import java.time.LocalDate;
import java.util.List;

public record PedidoCreate(
        LocalDate fecha,
        Estado estado,
        FormaPago formaPago,
        List<DetallePedidoCreate> detalles
) {
}
