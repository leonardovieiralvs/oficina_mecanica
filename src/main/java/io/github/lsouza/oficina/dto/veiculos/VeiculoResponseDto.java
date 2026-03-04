package io.github.lsouza.oficina.dto.veiculos;

import io.github.lsouza.oficina.dto.clientes.ClienteResponseDto;

import java.util.UUID;

public record VeiculoResponseDto(UUID id,
                                 String placa,
                                 String modelo,
                                 Integer ano,
                                 ClienteResponseDto cliente){}
