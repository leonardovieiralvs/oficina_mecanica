package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.ClienteRequestDto;
import io.github.lsouza.oficina.dto.ClienteResponseDto;
import io.github.lsouza.oficina.exceptions.ConflictException;
import io.github.lsouza.oficina.mappers.ClienteMapper;
import io.github.lsouza.oficina.models.Cliente;
import io.github.lsouza.oficina.models.Veiculo;
import io.github.lsouza.oficina.repository.ClienteRepository;
import io.github.lsouza.oficina.repository.VeiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
        return clienteMapper.toResponseEntity(clienteResultado);
    }

    public List<ClienteResponseDto> listarTodos() {
        List<Cliente> all = clienteRepository.findAll();
        return all.stream().map(clienteMapper::toResponseEntity).toList();
    }

    public ClienteResponseDto salvarCliente(ClienteRequestDto clienteDto) {

        if (clienteRepository.existsByCpf(clienteDto.cpf())) {
            throw new ConflictException("CPF já cadastrado no banco");
        }

        Cliente cliente = clienteMapper.toEntityRequest(clienteDto);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        return clienteMapper.toResponseEntity(clienteSalvo);
    }

    public ClienteResponseDto atualizarCliente(UUID id, ClienteRequestDto clienteDto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        clienteMapper.atualizar(cliente, clienteDto);

        return clienteMapper.toResponseEntity(cliente);
    }

    public void deleteById(UUID id) {
        Cliente clienteNotFound = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        clienteRepository.deleteById(id);
    }

}
