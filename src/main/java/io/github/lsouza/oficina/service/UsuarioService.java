package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.usuario.UsuarioDto;
import io.github.lsouza.oficina.mappers.UsuarioMapper;
import io.github.lsouza.oficina.models.Usuario;
import io.github.lsouza.oficina.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.encoder = encoder;
    }

    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);

        usuario.setSenha(encoder.encode(usuario.getSenha()));


        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuarioSalvo);
    }


    public Usuario salvarUsuario2(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }


    public UsuarioDto procurarPorLogin(String login) {
        Usuario usuarioLogin = usuarioRepository.findByLogin(login);
        return usuarioMapper.toDto(usuarioLogin);
    }
}
