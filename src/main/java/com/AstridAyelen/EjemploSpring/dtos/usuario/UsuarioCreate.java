package com.AstridAyelen.EjemploSpring.dtos.usuario;

import com.AstridAyelen.EjemploSpring.entity.Usuario;
import com.AstridAyelen.EjemploSpring.enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCreate(

        @NotBlank(message = "El nombre no puede estar vacio.")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacio.")
        String apellido,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato del correo electronico no es valido")
        String mail,

        @NotBlank(message = "El celular es obligatorio")
        String celular,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contraseña,

        Rol rol
) {
    public Usuario toEntity() {
        return Usuario.builder()
                .nombre(this.nombre())
                .apellido(this.apellido())
                .mail(this.mail())
                .celular(this.celular())
                .contraseña(this.contraseña())
                .rol(this.rol())
                .build();
    }
}
