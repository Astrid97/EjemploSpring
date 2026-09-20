package com.AstridAyelen.EjemploSpring.service;

import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioCreate;
import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioDto;
import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioEdit;
import java.util.List;

public interface UsuarioService {

    UsuarioDto save(UsuarioCreate usuarioCreate);

    UsuarioDto findById(Long id);

    UsuarioDto findByMail(String mail);

    List<UsuarioDto> findAll();

    UsuarioDto update(UsuarioEdit usuarioEdit, Long idUsuario);

    void deleteById(Long id);
}