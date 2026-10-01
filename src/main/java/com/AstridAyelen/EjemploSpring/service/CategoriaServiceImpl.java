package com.AstridAyelen.EjemploSpring.service;

import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaCreate;
import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaDto;
import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaEdit;
import com.AstridAyelen.EjemploSpring.entity.Categoria;
import com.AstridAyelen.EjemploSpring.entity.Producto;
import com.AstridAyelen.EjemploSpring.repository.CategoriaRepository;
import com.AstridAyelen.EjemploSpring.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public CategoriaDto save(CategoriaCreate categoriaCreate) {
        Categoria categoriaCruda = categoriaCreate.toEntity();
        Categoria categoriaGuardada = categoriaRepository.save(categoriaCruda);
        return CategoriaDto.toDto(categoriaGuardada);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaDto findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return CategoriaDto.toDto(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDto> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(CategoriaDto::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CategoriaDto update(CategoriaEdit categoriaEdit, Long idCategoria) {
        Categoria categoriaAEditar = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoriaEdit.applyTo(categoriaAEditar);

        Categoria categoriaGuardada = categoriaRepository.save(categoriaAEditar);
        return CategoriaDto.toDto(categoriaGuardada);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CategoriaDto agregarProducto(Long idCategoria, Long idProducto) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        categoria.getProductos().add(producto);

        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        return CategoriaDto.toDto(categoriaGuardada);
    }
}