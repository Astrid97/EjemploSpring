package com.AstridAyelen.EjemploSpring.dtos;

import java.util.List;

public record ErrorDto(
        int status,
        String mensaje,
        List<String> detalles
) {
}