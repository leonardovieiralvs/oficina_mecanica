package io.github.lsouza.oficina.dto.errors;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ErroRespostaDto(LocalDateTime timestamp, Integer status, String message, List<ErroCampo> errors, String path) {
}
