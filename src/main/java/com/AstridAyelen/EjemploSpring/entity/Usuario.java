package com.AstridAyelen.EjemploSpring.entity;

import com.AstridAyelen.EjemploSpring.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Usuario extends Base {

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, unique = true, length = 100)
    private String mail;

    @Column(nullable = false, length = 20)
    private String celular;

    @Column(nullable = false)
    private String contraseña;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    // Usuario -> Pedido en el UML: usuario conoce sus pedidos
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "usuario_id")
    private List<Pedido> pedidos = new ArrayList<>();
}
