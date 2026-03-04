package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.veiculos.VeiculoRequestDto;
import io.github.lsouza.oficina.dto.veiculos.VeiculoResponseDto;
import io.github.lsouza.oficina.mappers.VeiculoMapper;
import io.github.lsouza.oficina.models.Cliente;
import io.github.lsouza.oficina.models.OrdemServico;
import io.github.lsouza.oficina.models.Veiculo;
import io.github.lsouza.oficina.repository.ClienteRepository;
import io.github.lsouza.oficina.repository.OrdemServicoRepository;
import io.github.lsouza.oficina.repository.VeiculoRepository;
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

    public List<VeiculoResponseDto> listarTodos() {
        List<Veiculo> all = veiculoRepository.findAll();
        return all.stream().map(veiculoMapper::toResponseEntity).toList();
    }

    public VeiculoResponseDto salvarVeiculo(VeiculoRequestDto veiculoRequestDto) {
        Cliente cliente = clienteRepository.findById(veiculoRequestDto.idCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        Veiculo veiculo = veiculoMapper.toEntityRequest(veiculoRequestDto);
        veiculo.setCliente(cliente);

        System.out.println(veiculo.getCliente().getNome());

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        return veiculoMapper.toResponseEntity(veiculoSalvo);
    }
}
