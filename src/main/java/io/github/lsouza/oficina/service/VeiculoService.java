package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.veiculos.VeiculoRequestDto;
import io.github.lsouza.oficina.dto.veiculos.VeiculoResponseDto;
import io.github.lsouza.oficina.exceptions.ClienteNotFoundException;
import io.github.lsouza.oficina.exceptions.ConflictException;
import io.github.lsouza.oficina.exceptions.OperationNotAllowedException;
import io.github.lsouza.oficina.exceptions.VeiculoNotFoundException;
import io.github.lsouza.oficina.mappers.VeiculoMapper;
import io.github.lsouza.oficina.models.Cliente;
import io.github.lsouza.oficina.models.Veiculo;
import io.github.lsouza.oficina.repository.ClienteRepository;
import io.github.lsouza.oficina.repository.OrdemServicoRepository;
import io.github.lsouza.oficina.repository.VeiculoRepository;
import io.github.lsouza.oficina.repository.specs.VeiculoSpecificationRules;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    private ClienteRepository clienteRepository;
    private OrdemServicoRepository ordemRepository;
    private VeiculoMapper veiculoMapper;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository, OrdemServicoRepository ordemRepository, VeiculoMapper veiculoMapper) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
        this.ordemRepository = ordemRepository;
        this.veiculoMapper = veiculoMapper;
    }

    public VeiculoResponseDto pesquisarPorId(UUID id) {
        Veiculo veiculoId = veiculoRepository.findById(id).orElseThrow(() -> new VeiculoNotFoundException("Id", "Id não encontrado."));
        return veiculoMapper.toResponseDto(veiculoId);
    }

    public Page<VeiculoResponseDto> pesquisarPorPage(String placa, String modelo, Integer ano, Integer numeroPagina, Integer tamanhoPagina) {

        Specification<Veiculo> spec = Specification.allOf();

        if (placa != null) {
            spec = spec.and(VeiculoSpecificationRules.placaEquals(placa));
        }

        if (modelo != null) {
            spec = spec.and(VeiculoSpecificationRules.modeloLike(modelo));
        }

        if (ano != null) {
            spec = spec.and(VeiculoSpecificationRules.anoEquals(ano));
        }

        PageRequest pageRequest = PageRequest.of(numeroPagina, tamanhoPagina);

        Page<Veiculo> veiculos = veiculoRepository.findAll(spec, pageRequest);
        return veiculos.map(veiculoMapper::toResponseDto);
    }

    public VeiculoResponseDto salvarVeiculo(VeiculoRequestDto veiculoRequest) {
        Cliente cliente = clienteRepository.findById(veiculoRequest.idCliente()).orElseThrow(() -> new ClienteNotFoundException("idCliente", "Cliente não encontrado"));

        if (veiculoRepository.existsByPlaca(veiculoRequest.placa())) {
            throw new OperationNotAllowedException("placa", "Placa já existente no banco");
        }

        Veiculo veiculo = veiculoMapper.toVeiculoEntity(veiculoRequest);
        veiculo.setCliente(cliente);

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        return veiculoMapper.toResponseDto(veiculoSalvo);
    }

    public void atualizarVeiculo(UUID id, VeiculoRequestDto request) {
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() -> new VeiculoNotFoundException("id", "Veiculo não encontrado para atualização"));

        veiculoMapper.updateEntity(veiculo, request);
    }

    public void deletarVeiculo(UUID id) {
        if (ordemRepository.existsByVeiculo_id(id)) {
            throw new ConflictException("Não é possivel deletar um Veiculo com ordem de serviço aberta.");
        };
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(() -> new VeiculoNotFoundException("id", "Veiculo não encontrado para deleção"));
        veiculoRepository.delete(veiculo);
    }
}
