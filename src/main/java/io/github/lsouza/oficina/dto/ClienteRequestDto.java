package io.github.lsouza.oficina.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ClienteRequestDto(@NotBlank(message = "Campo obrigatório")
                                String nome,
                                @NotBlank(message = "Campo obrigatório")
                                @Size(min = 11, max = 11, message = "Cpf inválido")
                                String cpf,
                                @NotBlank(message = "Campo obrigatório")
                                String telefone,
                                @NotNull(message = "O cliente deve possuir um veículo.")
                                UUID idVeiculo) {
}
