package io.github.lsouza.oficina.dto.usuario;

import io.github.lsouza.oficina.enums.UserRoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(@NotNull String login,
                         @NotNull String senha,
                         @NotNull @Enumerated(EnumType.STRING) UserRoles roles) {
}
