package com.AstridAyelen.EjemploSpring.dtos.usuario;

import com.AstridAyelen.EjemploSpring.entity.Usuario;
import com.AstridAyelen.EjemploSpring.enums.Rol;

public record UsuarioDto(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular,
        Rol rol

) {
    public static UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getMail(),
                usuario.getCelular(),
                usuario.getRol()
        );
    }
}