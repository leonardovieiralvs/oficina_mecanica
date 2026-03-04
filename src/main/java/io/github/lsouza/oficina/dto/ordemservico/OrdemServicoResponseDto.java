package io.github.lsouza.oficina.dto.ordemservico;

import io.github.lsouza.oficina.enums.StatusOS;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrdemServicoResponseDto(UUID id,
                                      String descricao,
                                      BigDecimal valor,
                                      StatusOS status,
                                      LocalDateTime dataAbertura) {
}