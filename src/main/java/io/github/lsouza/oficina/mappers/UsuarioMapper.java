package io.github.lsouza.oficina.mappers;

import io.github.lsouza.oficina.dto.usuario.RegistrarUsuarioDto;
import io.github.lsouza.oficina.dto.usuario.UsuarioResponseDto;
import io.github.lsouza.oficina.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(RegistrarUsuarioDto dto);

    UsuarioResponseDto toDto(Usuario usuario);
}
