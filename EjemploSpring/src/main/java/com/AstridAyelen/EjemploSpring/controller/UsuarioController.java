package com.AstridAyelen.EjemploSpring.controller;

import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioCreate;
import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioDto;
import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioEdit;
import com.AstridAyelen.EjemploSpring.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> buscarTodos() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/mail/{mail}")
    public ResponseEntity<UsuarioDto> buscarPorMail(@PathVariable("mail") String mail) {
        return ResponseEntity.ok(usuarioService.findByMail(mail));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> crear(@RequestBody @Valid UsuarioCreate peticion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(peticion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> editar(@PathVariable Long id, @RequestBody @Valid UsuarioEdit peticion) {
        return ResponseEntity.ok(usuarioService.update(peticion, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}