package io.github.lsouza.oficina.controller;

import io.github.lsouza.oficina.dto.ClienteRequestDto;
import io.github.lsouza.oficina.dto.ClienteResponseDto;
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

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> listarTodos() {
        List<ClienteResponseDto> listarTodos = clienteService.listarTodos();
        return ResponseEntity.ok(listarTodos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> procurarClientePorId(@PathVariable UUID id) {
        return ResponseEntity.ok(clienteService.procurarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> salvarCliente(@RequestBody @Valid ClienteRequestDto clienteDto) {

        ClienteResponseDto clienteSalvo = clienteService.salvarCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> atualizarCliente(@PathVariable UUID id,
                                                               @RequestBody @Valid ClienteRequestDto clienteDto) {
        ClienteResponseDto clienteResponseDto = clienteService.atualizarCliente(id, clienteDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clienteResponseDto);


    }
}
