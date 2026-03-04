package io.github.lsouza.oficina.dto.veiculos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VeiculoRequestDto(@NotBlank(message = "Campo obrigatório")
                                String placa,
                                @NotBlank(message = "Campo obrigatório")
                                String modelo,
                                @NotNull(message = "Campo obrigatório")
                                Integer ano,
                                UUID idCliente) {
}
