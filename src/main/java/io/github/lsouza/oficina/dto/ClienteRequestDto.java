package io.github.lsouza.oficina.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDto(@NotBlank(message = "Campo obrigatório")
                                String nome,
                                @NotBlank(message = "Campo obrigatório")
                                @Size(min = 11, max = 11, message = "Cpf inválido")
                                String cpf,
                                @NotBlank(message = "Campo obrigatório")
                                String telefone) {
}
