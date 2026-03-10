package io.github.lsouza.oficina.repository;

import io.github.lsouza.oficina.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID>, JpaSpecificationExecutor<Veiculo> {


    boolean existsByCliente_id(UUID id);

    boolean existsByPlaca(String placa);

}
