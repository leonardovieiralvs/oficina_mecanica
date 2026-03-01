package io.github.lsouza.oficina.mappers;

import io.github.lsouza.oficina.dto.ClienteRequestDto;
import io.github.lsouza.oficina.dto.ClienteResponseDto;
import io.github.lsouza.oficina.models.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteResponseDto clienteDto);

    Cliente toRequestEntity(ClienteRequestDto clienteRequestDto);

    ClienteResponseDto toResponseDto(Cliente cliente);

    ClienteRequestDto toRequestDto(Cliente cliente);



}
