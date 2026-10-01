package com.AstridAyelen.EjemploSpring.service;

import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoCreate;
import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoDto;
import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoEdit;
import java.util.List;

public interface ProductoService {

    ProductoDto save(ProductoCreate productoCreate);

    ProductoDto findById(Long id);

    List<ProductoDto> findAll();

    ProductoDto update(ProductoEdit productoEdit, Long idProducto);

    void deleteById(Long id);
}
