package io.github.lsouza.oficina.mappers;

import io.github.lsouza.oficina.dto.usuario.UsuarioDto;
import io.github.lsouza.oficina.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDto dto);

    UsuarioDto toDto(Usuario usuario);
}
