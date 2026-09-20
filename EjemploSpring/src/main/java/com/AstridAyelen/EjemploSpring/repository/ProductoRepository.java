package com.AstridAyelen.EjemploSpring.repository;

import com.AstridAyelen.EjemploSpring.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}