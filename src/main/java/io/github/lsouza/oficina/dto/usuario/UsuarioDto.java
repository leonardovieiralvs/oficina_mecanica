package io.github.lsouza.oficina.dto.usuario;

import io.github.lsouza.oficina.enums.UserRoles;

public record UsuarioDto(String login,
                         String senha,
                         UserRoles roles) {
}
