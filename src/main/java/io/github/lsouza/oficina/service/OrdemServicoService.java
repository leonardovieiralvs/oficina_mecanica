package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoRequestDto;
import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoResponseDto;
import io.github.lsouza.oficina.enums.StatusOS;
import io.github.lsouza.oficina.exceptions.OrdemNotFoundException;
import io.github.lsouza.oficina.exceptions.VeiculoNotFoundException;
import io.github.lsouza.oficina.mappers.OrdemMapper;
import io.github.lsouza.oficina.models.OrdemServico;
import io.github.lsouza.oficina.models.Veiculo;
import io.github.lsouza.oficina.repository.OrdemServicoRepository;
import io.github.lsouza.oficina.repository.VeiculoRepository;
import io.github.lsouza.oficina.repository.specs.OrdemSpecificationRules;
import io.github.lsouza.oficina.validator.OrdemServicoValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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


    public OrdemServicoResponseDto pesquisarPorId(UUID id) {
        OrdemServico ordemId = ordemServicoRepository.findById(id).orElseThrow(() -> new OrdemNotFoundException("Id", "Ordem de serviço não encontrada."));
        return ordemMapper.toResponseDto(ordemId);
    }

    public Page<OrdemServicoResponseDto> pesquisarPorPage(String descricao, BigDecimal valorMin, BigDecimal valorMax, StatusOS status, Integer numeroPagina, Integer tamanhoPagina) {

        Specification<OrdemServico> spec = Specification.allOf();

        if (descricao != null) {
            spec = spec.and(OrdemSpecificationRules.descricaoLike(descricao));
        }

        if (valorMin != null) {
            spec = spec.and(OrdemSpecificationRules.valorMin(valorMin));
        }

        if (valorMax != null) {
            spec = spec.and(OrdemSpecificationRules.valorMax(valorMax));
        }

        if (status != null) {
            spec = spec.and(OrdemSpecificationRules.statusEquals(status));
        }

        PageRequest pageRequest = PageRequest.of(numeroPagina, tamanhoPagina);

        Page<OrdemServico> ordens = ordemServicoRepository.findAll(spec, pageRequest);

        return ordens.map(ordemMapper::toResponseDto);
    }

    public OrdemServicoResponseDto salvarOrdem(OrdemServicoRequestDto ordemRequest) {
        Veiculo veiculo = veiculoRepository.findById(ordemRequest.idVeiculo()).orElseThrow(() -> new VeiculoNotFoundException("idVeiculo", "Veiculo não encontrado"));
        OrdemServico ordemServico = ordemMapper.toOrdemEntity(ordemRequest);

        ordemServico.setVeiculo(veiculo);

        OrdemServico save = ordemServicoRepository.save(ordemServico);

        return ordemMapper.toResponseDto(save);
    }


    public void atualizarOrdem(UUID id, OrdemServicoRequestDto ordemRequest) {
        OrdemServico ordemServico = ordemServicoRepository.findById(id)
                .orElseThrow(() -> new OrdemNotFoundException("Id", "Ordem não encontrada"));

        // validar a transição usando o status atual da entidade (antes do mapper sobrescrever)
        OrdemServicoValidator.validarTransicao(ordemServico, ordemRequest.status());

        ordemMapper.updateEntity(ordemServico, ordemRequest);

        // segurança extra: se ficou CONCLUIDA e dataConclusao ainda está nula, preenche com a data atual
        if (ordemServico.getStatus() == StatusOS.CONCLUIDA && ordemServico.getDataConclusao() == null) {
            ordemServico.setDataConclusao(LocalDate.now());
        }

        ordemServicoRepository.save(ordemServico);
    }


    public void deletarPorId(UUID id) {
        OrdemServico ordemId = ordemServicoRepository.findById(id).orElseThrow(() -> new OrdemNotFoundException("Id", "Ordem de serviço não encontrada."));
        ordemServicoRepository.delete(ordemId);
    }
}
