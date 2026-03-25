package io.github.lsouza.oficina.dto.usuario;

import io.github.lsouza.oficina.enums.UserRoles;

public record RegistrarUsuarioDto(String login,
                                  String senha,
                                  UserRoles roles) {
}
