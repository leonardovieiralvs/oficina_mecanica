package io.github.lsouza.oficina.repository;

import io.github.lsouza.oficina.models.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, UUID> {

    boolean existsByVeiculo_id(UUID id);
}
