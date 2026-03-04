package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoRequestDto;
import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoResponseDto;
import io.github.lsouza.oficina.mappers.OrdemMapper;
import io.github.lsouza.oficina.models.OrdemServico;
import io.github.lsouza.oficina.repository.OrdemServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;
    private OrdemMapper ordemMapper;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, OrdemMapper ordemMapper) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.ordemMapper = ordemMapper;
    }


    public OrdemServicoResponseDto salvarOrdem(OrdemServicoRequestDto dtoRequest) {

        OrdemServico ordemServico = ordemMapper.toOrdemEntity(dtoRequest);

        OrdemServico save = ordemServicoRepository.save(ordemServico);

        return ordemMapper.toResponseRequest(save);

    }
}
