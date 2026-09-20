package com.AstridAyelen.EjemploSpring.entity;


import com.AstridAyelen.EjemploSpring.enums.Estado;
import com.AstridAyelen.EjemploSpring.enums.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Pedido extends Base implements Calculable {

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(nullable = false)
    private Double total;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPago formaPago;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<DetallePedido> detalles = new ArrayList<>();

    // Implementación de la interfaz Calculable
    @Override
    public void calcularTotal() {
        this.total = this.detalles.stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    // Métodos utilitarios
    public void addDetallePedido(int cantidad, Producto producto) {
        DetallePedido detalle = DetallePedido.builder()
                .cantidad(cantidad)
                .producto(producto)
                .subtotal(cantidad * producto.getPrecio())
                .build();
        this.detalles.add(detalle);
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        return this.detalles.stream()
                .filter(d -> d.getProducto().getId().equals(producto.getId()))
                .findFirst()
                .orElse(null);
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        this.detalles.removeIf(d -> d.getProducto().getId().equals(producto.getId()));
    }
}
