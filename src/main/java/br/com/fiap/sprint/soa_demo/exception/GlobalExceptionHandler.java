package br.com.fiap.sprint.soa_demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RecursoNaoEncontradoException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        return Map.of("erro", ex.getMessage());
    }

    @ExceptionHandler({LimiteExcedidoException.class, UsuarioAutoExcluidoException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleRegraDeNegocio(RuntimeException ex) {
        return Map.of("erro", ex.getMessage());
    }

    // Manipula os erros de validação do DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}