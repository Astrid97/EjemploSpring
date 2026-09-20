package com.AstridAyelen.EjemploSpring.service;

import com.AstridAyelen.EjemploSpring.dtos.detallePedido.DetallePedidoCreate;
import com.AstridAyelen.EjemploSpring.dtos.pedido.PedidoCreate;
import com.AstridAyelen.EjemploSpring.dtos.pedido.PedidoDto;
import com.AstridAyelen.EjemploSpring.dtos.pedido.PedidoEdit;
import com.AstridAyelen.EjemploSpring.entity.DetallePedido;
import com.AstridAyelen.EjemploSpring.entity.Pedido;
import com.AstridAyelen.EjemploSpring.entity.Producto;
import com.AstridAyelen.EjemploSpring.repository.PedidoRepository;
import com.AstridAyelen.EjemploSpring.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public PedidoDto save(PedidoCreate pedidoCreate) {
        Pedido pedido = new Pedido();
        pedido.setFecha(pedidoCreate.fecha());
        pedido.setEstado(pedidoCreate.estado());
        pedido.setFormaPago(pedidoCreate.formaPago());

        List<DetallePedido> detalles = new ArrayList<>();

        for (DetallePedidoCreate detalleCreate : pedidoCreate.detalles()) {
            // Buscamos el producto real para sacar su precio
            Producto producto = productoRepository.findById(detalleCreate.idProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detalleCreate.idProducto()));

            DetallePedido detalle = new DetallePedido();
            detalle.setCantidad(detalleCreate.cantidad());
            detalle.setProducto(producto); // Asignamos la entidad producto
            detalle.setSubtotal(detalleCreate.cantidad() * producto.getPrecio()); // Matemática segura

            // Si tenés relación bidireccional, esta línea es vital para que se guarde bien en la BD:
            // detalle.setPedido(pedido);

            detalles.add(detalle);
        }

        pedido.setDetalles(detalles);
        pedido.calcularTotal(); // Ahora sí, los subtotales existen y esto suma perfecto

        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return PedidoDto.toDto(pedidoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoDto findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + id));
        return PedidoDto.toDto(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDto> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(PedidoDto::toDto)
                .toList();
    }

    @Override
    @Transactional
    public PedidoDto update(PedidoEdit pedidoEdit, Long idPedido) {
        Pedido pedidoAEditar = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + idPedido));

        // Solo aplica el cambio de Estado (ej. de PENDIENTE a ENTREGADO)
        pedidoEdit.applyTo(pedidoAEditar);

        Pedido pedidoGuardado = pedidoRepository.save(pedidoAEditar);
        return PedidoDto.toDto(pedidoGuardado);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
}