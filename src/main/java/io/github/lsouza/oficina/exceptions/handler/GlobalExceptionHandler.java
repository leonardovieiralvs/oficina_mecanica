package io.github.lsouza.oficina.exceptions.handler;

import io.github.lsouza.oficina.dto.ErroCampo;
import io.github.lsouza.oficina.dto.ErroRespostaDto;
import io.github.lsouza.oficina.exceptions.ConflictException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErroRespostaDto> conflictExceptionHandler(ConflictException e,
                                                                    HttpServletRequest request) {
        ErroRespostaDto path = ErroRespostaDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(path);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e,
                                                                                  HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<ErroCampo> error = fieldErrors.stream().map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage())).toList();
        ErroRespostaDto formatoInvalido = ErroRespostaDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Formato Json inválido")
                .errors(error)
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(formatoInvalido);
    }
}
