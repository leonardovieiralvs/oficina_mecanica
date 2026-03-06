package io.github.lsouza.oficina.controller;

import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoRequestDto;
import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoResponseDto;
import io.github.lsouza.oficina.models.OrdemServico;
import io.github.lsouza.oficina.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {

    private OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping
    public ResponseEntity<List<OrdemServicoResponseDto>> listarTodos() {
        List<OrdemServicoResponseDto> lista = ordemServicoService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponseDto> salvarOrdem(@RequestBody @Valid OrdemServicoRequestDto ordemRequest) {

        OrdemServicoResponseDto ordemSalva = ordemServicoService.salvarOrdem(ordemRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ordemSalva);

    }
}
