package com.AstridAyelen.EjemploSpring.controller;

import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaCreate;
import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaDto;
import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaEdit;
import com.AstridAyelen.EjemploSpring.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> buscarTodas() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> crear(@RequestBody @Valid CategoriaCreate peticion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(peticion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> editar(@PathVariable Long id, @RequestBody @Valid CategoriaEdit peticion) {
        return ResponseEntity.ok(categoriaService.update(peticion, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idCategoria}/productos/{idProducto}")
    public ResponseEntity<CategoriaDto> vincularProducto(
            @PathVariable Long idCategoria,
            @PathVariable Long idProducto) {
        return ResponseEntity.ok(categoriaService.agregarProducto(idCategoria, idProducto));
    }

}
