package com.AstridAyelen.EjemploSpring.service;

import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioCreate;
import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioDto;
import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioEdit;
import com.AstridAyelen.EjemploSpring.entity.Usuario;
import com.AstridAyelen.EjemploSpring.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioDto save(UsuarioCreate usuarioCreate) {
        // 1. Convertimos los datos del registro a una Entidad
        Usuario usuarioCrudo = usuarioCreate.toEntity();


        // 2. Guardamos en la base de datos H2
        Usuario usuarioGuardado = usuarioRepository.save(usuarioCrudo);

        // 3. Devolvemos el DTO
        return UsuarioDto.toDto(usuarioGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        return UsuarioDto.toDto(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto findByMail(String mail) {
        Usuario usuario = usuarioRepository.findByMailIgnoreCase(mail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con mail: " + mail));
        return UsuarioDto.toDto(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(UsuarioDto::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UsuarioDto update(UsuarioEdit usuarioEdit, Long idUsuario) {
        // 1. Buscamos el usuario
        Usuario usuarioAEditar = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        // 2. Aplicamos los cambios permitidos (nombre, apellido, celular)
        usuarioEdit.applyTo(usuarioAEditar);

        // 3. Guardamos y devolvemos de forma segura
        Usuario usuarioGuardado = usuarioRepository.save(usuarioAEditar);
        return UsuarioDto.toDto(usuarioGuardado);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}