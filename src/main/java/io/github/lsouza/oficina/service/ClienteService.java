package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.ClienteRequestDto;
import io.github.lsouza.oficina.dto.ClienteResponseDto;
import io.github.lsouza.oficina.mappers.ClienteMapper;
import io.github.lsouza.oficina.models.Cliente;
import io.github.lsouza.oficina.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResponseDto procurarPorId(UUID id) {
        Cliente clienteResultado = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return clienteMapper.toResponseDto(clienteResultado);
    }

    public ClienteResponseDto salvarCliente(ClienteRequestDto clienteDto) {
        if (clienteRepository.existsByCpf(clienteDto.cpf())) {
            throw new RuntimeException("CPF já cadastrado no banco");
        }

        Cliente cliente = clienteMapper.toResponseEntity(clienteDto);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return clienteMapper.toResponseDto(clienteSalvo);
    }


}
