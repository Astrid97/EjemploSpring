package com.AstridAyelen.EjemploSpring.repository;

import com.AstridAyelen.EjemploSpring.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}