package io.github.lsouza.oficina.mappers;

import io.github.lsouza.oficina.dto.veiculos.VeiculoRequestDto;
import io.github.lsouza.oficina.dto.veiculos.VeiculoResponseDto;
import io.github.lsouza.oficina.models.Veiculo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    Veiculo toEntityRequest(VeiculoRequestDto request);

    VeiculoResponseDto toResponseEntity(Veiculo veiculo);
}
