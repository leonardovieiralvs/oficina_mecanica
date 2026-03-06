package io.github.lsouza.oficina.dto.ordemservico;

import io.github.lsouza.oficina.enums.StatusOS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record OrdemServicoRequestDto(@NotBlank(message = "Campo obrigatório")
                                     String descricao,
                                     @NotNull(message = "Campo obrigatório")
                                     BigDecimal valor,
                                     @NotNull(message = "Campo obrigatório")
                                     StatusOS status,
                                     @NotNull(message = "Campo obrigatório")
                                     UUID idVeiculo) {
}





// OrdemServico
//--------------
//id: UUID
//descricao: String
//valor: BigDecimal
//status: StatusOS (ENUM)
//dataAbertura: LocalDate
//dataConclusao: LocalDate
//veiculo: Veiculo