package io.github.lsouza.oficina.controller;

import io.github.lsouza.oficina.dto.usuario.AutenticarUsuarioDto;
import io.github.lsouza.oficina.dto.usuario.RegistrarUsuarioDto;
import io.github.lsouza.oficina.dto.usuario.UsuarioResponseDto;
import io.github.lsouza.oficina.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class AuthController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDto> login(@RequestBody @Valid AutenticarUsuarioDto usuarioDto) {
        UsuarioResponseDto usuarioAutententicado = usuarioService.autenticarUsuario(usuarioDto);
        return ResponseEntity.ok(usuarioAutententicado);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> registrarUsuario(@RequestBody @Valid RegistrarUsuarioDto usuarioDto) {
        usuarioService.registrarUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
