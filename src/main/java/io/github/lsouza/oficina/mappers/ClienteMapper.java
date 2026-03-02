package io.github.lsouza.oficina.mappers;

import io.github.lsouza.oficina.dto.ClienteRequestDto;
import io.github.lsouza.oficina.dto.ClienteResponseDto;
import io.github.lsouza.oficina.models.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClienteMapper {


    Cliente toEntityRequest(ClienteRequestDto clienteRequestDto);

    ClienteResponseDto toResponseDto(Cliente cliente);

    void atualizar(@MappingTarget Cliente cliente, ClienteRequestDto clienteDto);

}
