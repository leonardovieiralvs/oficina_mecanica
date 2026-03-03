package io.github.lsouza.oficina.dto;

import java.util.UUID;

public record ClienteResponseDto(UUID id,
                                 String nome,
                                 String cpf,
                                 String telefone) {
}
