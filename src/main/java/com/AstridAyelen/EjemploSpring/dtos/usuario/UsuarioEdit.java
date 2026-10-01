package com.AstridAyelen.EjemploSpring.dtos.usuario;

import com.AstridAyelen.EjemploSpring.entity.Usuario;

public record UsuarioEdit(
        String nombre,
        String apellido,
        String celular

) {
    public void applyTo(Usuario usuario) {
        if (this.nombre != null) {
            usuario.setNombre(this.nombre);
        }
        if (this.apellido != null) {
            usuario.setApellido(this.apellido);
        }
        if (this.celular != null) {
            usuario.setCelular(this.celular);
        }
    }
}