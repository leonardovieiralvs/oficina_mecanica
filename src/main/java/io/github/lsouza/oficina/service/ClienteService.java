package io.github.lsouza.oficina.service;

import io.github.lsouza.oficina.dto.clientes.ClienteRequestDto;
import io.github.lsouza.oficina.dto.clientes.ClienteResponseDto;
import io.github.lsouza.oficina.exceptions.ConflictException;
import io.github.lsouza.oficina.exceptions.OperationNotAllowedException;
import io.github.lsouza.oficina.mappers.ClienteMapper;
import io.github.lsouza.oficina.models.Cliente;
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
    private VeiculoRepository veiculoRepository;
    private ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, VeiculoRepository veiculoRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResponseDto pesquisarPorId(UUID id) {
        Cliente clienteId = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        return clienteMapper.toResponseEntity(clienteId);
    }

    public List<ClienteResponseDto> listarTodos() {
        List<Cliente> all = clienteRepository.findAll();
        return all.stream().map(clienteMapper::toResponseEntity).toList();
    }

    public ClienteResponseDto salvarCliente(ClienteRequestDto clienteRequest) {

        if (clienteRepository.existsByCpf(clienteRequest.cpf())) {
            throw new ConflictException("CPF já cadastrado no banco");
        }

        Cliente cliente = clienteMapper.toEntityRequest(clienteRequest);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        return clienteMapper.toResponseEntity(clienteSalvo);
    }

    public void atualizarCliente(UUID id, ClienteRequestDto clienteDto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        clienteMapper.atualizar(cliente, clienteDto);
    }

    public void deleteById(UUID id) {
        if (veiculoRepository.existsByCliente_id(id)) {
            throw new OperationNotAllowedException("cliente_id", "Não é possivel remover um cliente que possui um Veículo.");
        }
        clienteRepository.deleteById(id);
    }
}
