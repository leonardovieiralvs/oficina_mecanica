package io.github.lsouza.oficina.exceptions.handler;

import com.auth0.jwt.exceptions.JWTCreationException;
import io.github.lsouza.oficina.dto.errors.ErroCampo;
import io.github.lsouza.oficina.dto.errors.ErroRespostaDto;
import io.github.lsouza.oficina.exceptions.ConflictException;
import io.github.lsouza.oficina.exceptions.OperationNotAllowedException;
import io.github.lsouza.oficina.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
                .path(request.getRequestURI()).build();
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
                .message("Erro de validação").errors(error)
                .path(request.getRequestURI()).build();
        return ResponseEntity.badRequest().body(formatoInvalido);
    }

    @ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity<ErroRespostaDto> operationNotAllowedExceptionHandler(OperationNotAllowedException e,
                                                                      HttpServletRequest request) {
        ErroRespostaDto errorResponse = ErroRespostaDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .message("Operation Not Allowed")
                .errors(List.of(new ErroCampo(e.getCampo(), e.getMessage())))
                .path(request.getRequestURI()).build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroRespostaDto> resourceNotFoundExceptionHandler(ResourceNotFoundException e, HttpServletRequest request) {
        ErroRespostaDto errorResponse = ErroRespostaDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Recurso não encontrado.")
                .errors(List.of(new ErroCampo(e.getCampo(), e.getMessage())))
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroRespostaDto> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e,
                                                                                  HttpServletRequest request) {

        ErroRespostaDto errorResponse = ErroRespostaDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("JSON inválido ou campos com formato incorreto")
                .path(request.getRequestURI())
                .errors(List.of())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroRespostaDto> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e,
                                                                                  HttpServletRequest request) {

        ErroRespostaDto errorResponse = ErroRespostaDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message("ID informado possui formato inválido")
                .path(request.getRequestURI())
                .errors(List.of())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity<ErroRespostaDto> jwtCreationExceptionHandler(JWTCreationException e, HttpServletRequest request) {
        ErroRespostaDto errorResponse = ErroRespostaDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .errors(List.of())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
