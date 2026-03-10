package io.github.lsouza.oficina.controller;

import io.github.lsouza.oficina.dto.clientes.ClienteRequestDto;
import io.github.lsouza.oficina.dto.clientes.ClienteResponseDto;
import io.github.lsouza.oficina.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> pesquisarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.pesquisarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarTodos() {
        List<ClienteResponseDto> listarTodos = clienteService.listarTodos();
        return ResponseEntity.ok(listarTodos);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> salvarCliente(@RequestBody @Valid ClienteRequestDto clienteDto) {

        ClienteResponseDto clienteSalvo = clienteService.salvarCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarCliente(@PathVariable UUID id,
                                                 @RequestBody @Valid ClienteRequestDto clienteDto) {
        clienteService.atualizarCliente(id, clienteDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable UUID id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
