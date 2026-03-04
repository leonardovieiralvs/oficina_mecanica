package io.github.lsouza.oficina.controller;

import io.github.lsouza.oficina.dto.veiculos.VeiculoRequestDto;
import io.github.lsouza.oficina.dto.veiculos.VeiculoResponseDto;
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
    public ResponseEntity<VeiculoResponseDto> salvarVeiculo(@RequestBody @Valid VeiculoRequestDto veiculoRequestDto) {

        VeiculoResponseDto veiculoSalvo = veiculoService.salvarVeiculo(veiculoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }
}
