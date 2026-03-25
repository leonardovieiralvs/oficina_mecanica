package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.usuario.AutenticarUsuarioDto;
import io.github.lsouza.oficina.dto.usuario.RegistrarUsuarioDto;
import io.github.lsouza.oficina.dto.usuario.UsuarioResponseDto;
import io.github.lsouza.oficina.exceptions.ConflictException;
import io.github.lsouza.oficina.infra.security.TokenService;
import io.github.lsouza.oficina.mappers.UsuarioMapper;
import io.github.lsouza.oficina.models.Usuario;
import io.github.lsouza.oficina.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder encoder;


    public UsuarioResponseDto registrarUsuario(RegistrarUsuarioDto usuarioDto) {
        if (usuarioRepository.findByLogin(usuarioDto.login()).isPresent()) {
            throw new ConflictException("Login já existente.");
        }

        Usuario usuario = usuarioMapper.toEntity(usuarioDto);

        usuario.setSenha(encoder.encode(usuarioDto.senha()));

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.toDto(usuarioSalvo);
    }

    public UsuarioResponseDto autenticarUsuario(AutenticarUsuarioDto usuarioDto) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioDto.login(), usuarioDto.senha()));
        Usuario usuarioAuth = (Usuario) auth.getPrincipal();

        String token = tokenService.criarToken(usuarioAuth);

        return new UsuarioResponseDto(usuarioAuth.getLogin(), token);
    }
}
