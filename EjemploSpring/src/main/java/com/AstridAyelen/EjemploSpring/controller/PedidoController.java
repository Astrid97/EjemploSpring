package com.AstridAyelen.EjemploSpring.controller;

import com.AstridAyelen.EjemploSpring.dtos.pedido.PedidoCreate;
import com.AstridAyelen.EjemploSpring.dtos.pedido.PedidoDto;
import com.AstridAyelen.EjemploSpring.dtos.pedido.PedidoEdit;
import com.AstridAyelen.EjemploSpring.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDto>> buscarTodos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoDto> crear(@RequestBody PedidoCreate pedidoCreate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedidoCreate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDto> editar(@PathVariable Long id, @RequestBody @Valid PedidoEdit peticion) {
        return ResponseEntity.ok(pedidoService.update(peticion, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}