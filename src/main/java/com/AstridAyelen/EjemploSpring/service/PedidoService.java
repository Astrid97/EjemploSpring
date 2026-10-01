package com.AstridAyelen.EjemploSpring.service;

import com.AstridAyelen.EjemploSpring.dtos.pedido.PedidoCreate;
import com.AstridAyelen.EjemploSpring.dtos.pedido.PedidoDto;
import com.AstridAyelen.EjemploSpring.dtos.pedido.PedidoEdit;

import java.util.List;

public interface PedidoService {

    // Recibe la Entidad
    PedidoDto save(PedidoCreate pedidoCreate);

    PedidoDto findById(Long id);

    List<PedidoDto> findAll();

    PedidoDto update(PedidoEdit pedidoEdit, Long idPedido);

    void deleteById(Long id);
}