package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoRequestDto;
import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoResponseDto;
import io.github.lsouza.oficina.dto.veiculos.VeiculoResponseDto;
import io.github.lsouza.oficina.exceptions.VeiculoNotFoundException;
import io.github.lsouza.oficina.mappers.OrdemMapper;
import io.github.lsouza.oficina.models.OrdemServico;
import io.github.lsouza.oficina.models.Veiculo;
import io.github.lsouza.oficina.repository.OrdemServicoRepository;
import io.github.lsouza.oficina.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class OrdemServicoService {

    private OrdemServicoRepository ordemServicoRepository;
    private VeiculoRepository veiculoRepository;
    private OrdemMapper ordemMapper;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, VeiculoRepository veiculoRepository, OrdemMapper ordemMapper) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.veiculoRepository = veiculoRepository;
        this.ordemMapper = ordemMapper;
    }


    public List<OrdemServicoResponseDto> listarTodos() {
        List<OrdemServico> all = ordemServicoRepository.findAll();
        return all.stream().map(ordemMapper::toResponseEntity).toList();
    }

    public OrdemServicoResponseDto salvarOrdem(OrdemServicoRequestDto ordemRequest) {
        Veiculo veiculo = veiculoRepository.findById(ordemRequest.idVeiculo()).orElseThrow(() -> new VeiculoNotFoundException("idVeiculo", "Veiculo não encontrado"));
        OrdemServico ordemServico = ordemMapper.toOrdemEntity(ordemRequest);

        ordemServico.setVeiculo(veiculo);

        OrdemServico save = ordemServicoRepository.save(ordemServico);

        return ordemMapper.toResponseRequest(save);

    }
}
