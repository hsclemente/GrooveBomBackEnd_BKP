package br.com.hc.groove.bom.infra.advices;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionAdvice {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> error400(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(x -> {
            String message = x.getDefaultMessage();
            return new ValidError(x.getField(), switch (message) {
                                                    case "must not be blank", "não deve estar em branco" -> "O campo não pode ser vazio";
                                                    case "must not be null", "não deve ser nulo" -> "O campo não pode ser nulo"; 
                                                    case "must be a well-formed email address", "deve ser um endereço de e-mail bem formado" -> "O e-mail deve ser valido";
                                                    default -> x.getDefaultMessage();
                                                }
            );
        }).collect(Collectors.toList()));
    }

    private record ValidError(String field, String message) {}
}
