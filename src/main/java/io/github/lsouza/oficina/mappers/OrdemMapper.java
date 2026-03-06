package io.github.lsouza.oficina.mappers;

import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoRequestDto;
import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoResponseDto;
import io.github.lsouza.oficina.models.OrdemServico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdemMapper {


    OrdemServico toOrdemEntity(OrdemServicoRequestDto dtoRequest);

    OrdemServicoResponseDto toResponseEntity(OrdemServico servico);

    OrdemServicoResponseDto toResponseRequest(OrdemServico ordemServico);
}
