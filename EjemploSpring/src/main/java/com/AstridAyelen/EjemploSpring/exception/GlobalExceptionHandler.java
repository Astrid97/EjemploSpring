package com.AstridAyelen.EjemploSpring.exception;

import com.AstridAyelen.EjemploSpring.dtos.ErrorDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Errores de Validación de DTOs
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> detalles = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST.value(), "Error de validación en los datos enviados", detalles);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    // 2. Errores de Base de Datos HIBERNATE
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        // Spring envuelve los errores de constraints de Hibernate en DataIntegrityViolationException
        ErrorDto errorDto = new ErrorDto(
                HttpStatus.CONFLICT.value(),
                "Error de integridad en la base de datos (Hibernate)",
                List.of(ex.getMostSpecificCause().getMessage())
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
    }

    // 3. Errores de Lógica de Negocio
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeExceptions(RuntimeException ex) {
        ErrorDto errorDto = new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                "Recurso no encontrado o solicitud inválida",
                List.of(ex.getMessage())
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    // 4. Cualquier otro error no controlado (Falla general del servidor)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneric(Exception ex) {

        ex.printStackTrace();
        ErrorDto errorDto = new ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error interno del servidor",
                List.of(ex.getMessage())
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }

    // 5. Errores de formato en el JSON (Ej: mandan "arbol" en vez de un boolean o un número)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleJsonParseErrors(HttpMessageNotReadableException ex) {
        ErrorDto errorDto = new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                "Error en el formato de los datos enviados",
                List.of("Verifique que los tipos de datos sean correctos (ej: números válidos, true/false para booleanos).")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}