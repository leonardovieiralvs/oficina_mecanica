package io.github.lsouza.oficina.security;

import io.github.lsouza.oficina.dto.usuario.UsuarioDto;
import io.github.lsouza.oficina.service.UsuarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public CustomUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UsuarioDto usuarioDto = usuarioService.procurarPorLogin(login);
        if (usuarioDto == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return User.builder()
                .username(usuarioDto.login())
                .password(usuarioDto.senha())
                .roles(usuarioDto.roles().toString())
                .build();
    }
}
