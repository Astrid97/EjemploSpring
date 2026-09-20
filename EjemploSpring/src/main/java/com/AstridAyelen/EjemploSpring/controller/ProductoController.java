package com.AstridAyelen.EjemploSpring.controller;

import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoCreate;
import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoDto;
import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoEdit;
import com.AstridAyelen.EjemploSpring.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> buscarTodos() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDto> crear(@RequestBody @Valid ProductoCreate peticion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(peticion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> editar(@PathVariable Long id, @RequestBody @Valid ProductoEdit peticion) {
        return ResponseEntity.ok(productoService.update(peticion, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}