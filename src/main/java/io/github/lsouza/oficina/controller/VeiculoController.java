package io.github.lsouza.oficina.controller;

import io.github.lsouza.oficina.dto.veiculos.VeiculoRequestDto;
import io.github.lsouza.oficina.dto.veiculos.VeiculoResponseDto;
import io.github.lsouza.oficina.models.Veiculo;
import io.github.lsouza.oficina.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDto> pesquisarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(veiculoService.pesquisarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<VeiculoResponseDto>> pesquisarPorPage(@RequestParam(name = "placa", required = false) String placa,
                                                                     @RequestParam(name = "modelo", required = false) String modelo,
                                                                     @RequestParam(name = "ano", required = false) Integer ano,
                                                                     @RequestParam(name = "numero-pagina", defaultValue = "0") Integer numeroPagina,
                                                                     @RequestParam(name = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina) {

        Page<VeiculoResponseDto> veiculosPage = veiculoService.pesquisarPorPage(placa, modelo, ano, numeroPagina, tamanhoPagina);
        return ResponseEntity.ok(veiculosPage);
    }

    @PostMapping
    public ResponseEntity<VeiculoResponseDto> salvarVeiculo(@RequestBody @Valid VeiculoRequestDto veiculoRequestDto) {

        VeiculoResponseDto veiculoSalvo = veiculoService.salvarVeiculo(veiculoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarVeiculo(@PathVariable UUID id, @RequestBody @Valid VeiculoRequestDto request) {
        veiculoService.atualizarVeiculo(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVeiculo(@PathVariable UUID id) {
        veiculoService.deletarVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}
