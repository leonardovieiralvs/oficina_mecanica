package io.github.lsouza.oficina.mappers;

import io.github.lsouza.oficina.dto.VeiculoRequestDto;
import io.github.lsouza.oficina.dto.VeiculoResponseDto;
import io.github.lsouza.oficina.models.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    Veiculo toEntityRequest(VeiculoRequestDto request);

    VeiculoResponseDto toResponseEntity(Veiculo veiculo);
}
