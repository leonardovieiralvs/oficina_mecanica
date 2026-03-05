package io.github.lsouza.oficina.repository;

import io.github.lsouza.oficina.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {


    boolean existsByCliente_id(UUID id);

    boolean existsByPlaca(String placa);

}
