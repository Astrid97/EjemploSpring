package com.AstridAyelen.EjemploSpring.service;

import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaCreate;
import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaDto;
import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaEdit;
import java.util.List;

public interface CategoriaService {

    CategoriaDto save(CategoriaCreate categoriaCreate);

    CategoriaDto findById(Long id);

    List<CategoriaDto> findAll();

    CategoriaDto update(CategoriaEdit categoriaEdit, Long idCategoria);

    void deleteById(Long id);

    CategoriaDto agregarProducto(Long idCategoria, Long idProducto);
}