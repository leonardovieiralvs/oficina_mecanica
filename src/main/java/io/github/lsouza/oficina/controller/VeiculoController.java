package io.github.lsouza.oficina.controller;

import io.github.lsouza.oficina.dto.VeiculoRequestDto;
import io.github.lsouza.oficina.dto.VeiculoResponseDto;
import io.github.lsouza.oficina.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDto>> listarTodos() {
        return ResponseEntity.ok(veiculoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDto> salvarVeiculo(UUID id, @RequestBody @Valid VeiculoRequestDto veiculoRequestDto) {

        VeiculoResponseDto veiculoSalvo = veiculoService.salvarCliente(id, veiculoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }
}
