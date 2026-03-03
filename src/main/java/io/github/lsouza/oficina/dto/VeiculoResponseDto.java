package io.github.lsouza.oficina.dto;

import java.util.List;
import java.util.UUID;

public record VeiculoResponseDto(UUID id,
                                 String placa,
                                 String modelo,
                                 Integer ano,
                                 ClienteResponseDto cliente) {
}
