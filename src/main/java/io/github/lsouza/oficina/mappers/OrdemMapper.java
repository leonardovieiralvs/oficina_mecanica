package io.github.lsouza.oficina.mappers;

import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoRequestDto;
import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoResponseDto;
import io.github.lsouza.oficina.models.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrdemMapper {


    OrdemServico toOrdemEntity(OrdemServicoRequestDto request);

    OrdemServicoResponseDto toResponseEntity(OrdemServico servico);

    OrdemServicoResponseDto toResponseRequest(OrdemServico ordemServico);

    void updateEntity(@MappingTarget OrdemServico ordem, OrdemServicoRequestDto request);
}
