package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.VeiculoRequestDto;
import io.github.lsouza.oficina.dto.VeiculoResponseDto;
import io.github.lsouza.oficina.mappers.VeiculoMapper;
import io.github.lsouza.oficina.models.Veiculo;
import io.github.lsouza.oficina.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private VeiculoRepository veiculoRepository;
    private VeiculoMapper veiculoMapper;

    public VeiculoService(VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper) {
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper = veiculoMapper;
    }

    public List<VeiculoResponseDto> listarTodos() {
        List<Veiculo> all = veiculoRepository.findAll();
        return all.stream().map(veiculoMapper::toResponseEntity).toList();
    }

    public VeiculoResponseDto salvarCliente(VeiculoRequestDto veiculoRequestDto) {

        Veiculo veiculo = veiculoMapper.toEntityRequest(veiculoRequestDto);
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        return veiculoMapper.toResponseEntity(veiculoSalvo);
    }
}
