package io.github.lsouza.oficina.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDto(@NotBlank(message = "Campo obrigatório") String nome,
                                @NotBlank(message = "Campo obrigatório") String cpf,
                                @NotBlank(message = "Campo obrigatório") String telefone) {}
