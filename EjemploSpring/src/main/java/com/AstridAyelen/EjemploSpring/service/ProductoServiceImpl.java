package com.AstridAyelen.EjemploSpring.service;

import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoCreate;
import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoDto;
import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoEdit;
import com.AstridAyelen.EjemploSpring.entity.Producto;
import com.AstridAyelen.EjemploSpring.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public ProductoDto save(ProductoCreate productoCreate) {
        // 1. Convertimos el DTO de entrada en Entidad
        Producto productoCrudo = productoCreate.toEntity();

        // 2. Guardamos en H2
        Producto productoGuardado = productoRepository.save(productoCrudo);

        // 3. Devolvemos el DTO de salida
        return ProductoDto.toDto(productoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDto findById(Long id) {
        // Buscamos el producto o lanzamos una excepción si no existe
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        return ProductoDto.toDto(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDto> findAll() {
        List<Producto> productos = productoRepository.findAll();

        // Mapeamos la lista de Entidades a lista de DTOs
        return productos.stream()
                .map(ProductoDto::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ProductoDto update(ProductoEdit productoEdit, Long idProducto) {
        // 1. Buscamos el producto existente
        Producto productoAEditar = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));

        // 2. Aplicamos solo los campos que vinieron con datos
        productoEdit.applyTo(productoAEditar);

        // 3. Guardamos y devolvemos
        Producto productoGuardado = productoRepository.save(productoAEditar);
        return ProductoDto.toDto(productoGuardado);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}