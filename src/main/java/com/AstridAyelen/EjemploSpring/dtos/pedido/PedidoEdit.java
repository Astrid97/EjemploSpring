package com.AstridAyelen.EjemploSpring.dtos.pedido;

import com.AstridAyelen.EjemploSpring.entity.Pedido;
import com.AstridAyelen.EjemploSpring.enums.Estado;

public record PedidoEdit(
        Estado estado
) {
    public void applyTo(Pedido pedido) {
        if (this.estado != null) {
            pedido.setEstado(this.estado);
        }
    }
}