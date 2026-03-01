package io.github.lsouza.oficina.dto;

import io.github.lsouza.oficina.models.Veiculo;

import java.util.List;

public record ClienteResponseDto(String nome,
                                 String cpf,
                                 String telefone,
                                 List<Veiculo> veiculoList) {
}
