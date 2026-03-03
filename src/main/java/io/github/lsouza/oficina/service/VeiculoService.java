package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.ClienteResponseDto;
import io.github.lsouza.oficina.dto.VeiculoRequestDto;
import io.github.lsouza.oficina.dto.VeiculoResponseDto;
import io.github.lsouza.oficina.mappers.ClienteMapper;
import io.github.lsouza.oficina.mappers.VeiculoMapper;
import io.github.lsouza.oficina.models.Cliente;
import io.github.lsouza.oficina.models.Veiculo;
import io.github.lsouza.oficina.repository.ClienteRepository;
import io.github.lsouza.oficina.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    private ClienteRepository clienteRepository;
    private VeiculoMapper veiculoMapper;
    private ClienteMapper clienteMapper;

    public VeiculoService(VeiculoRepository veiculoRepository, ClienteRepository clienteRepository, VeiculoMapper veiculoMapper, ClienteMapper clienteMapper) {
        this.veiculoRepository = veiculoRepository;
        this.clienteRepository = clienteRepository;
        this.veiculoMapper = veiculoMapper;
        this.clienteMapper = clienteMapper;
    }

    public List<VeiculoResponseDto> listarTodos() {
        List<Veiculo> all = veiculoRepository.findAll();
        return all.stream().map(veiculoMapper::toResponseEntity).toList();
    }

    public VeiculoResponseDto salvarCliente(UUID id, VeiculoRequestDto veiculoRequestDto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Veiculo veiculo = veiculoMapper.toEntityRequest(veiculoRequestDto);

        veiculo.setCliente(cliente);

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        return veiculoMapper.toResponseEntity(veiculoSalvo);
    }
}
