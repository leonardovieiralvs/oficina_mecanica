package io.github.lsouza.oficina.controller;

import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoRequestDto;
import io.github.lsouza.oficina.dto.ordemservico.OrdemServicoResponseDto;
import io.github.lsouza.oficina.enums.StatusOS;
import io.github.lsouza.oficina.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {

    private OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponseDto> pesquisarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ordemServicoService.pesquisarPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<OrdemServicoResponseDto>> pesquisarPorPage(@RequestParam(name = "descricao", required = false) String descricao,
                                                                          @RequestParam(name = "valor-min", required = false) BigDecimal valorMin,
                                                                          @RequestParam(name = "valor-max", required = false) BigDecimal valorMax,
                                                                          @RequestParam(name = "status", required = false) StatusOS status,
                                                                          @RequestParam(name = "numero-pagina", defaultValue = "0") Integer numeroPagina,
                                                                          @RequestParam(name = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina) {

        Page<OrdemServicoResponseDto> ordensPage = ordemServicoService.pesquisarPorPage(descricao, valorMin, valorMax, status, numeroPagina, tamanhoPagina);
        return ResponseEntity.ok(ordensPage);
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponseDto> salvarOrdem(@RequestBody @Valid OrdemServicoRequestDto ordemRequest) {

        OrdemServicoResponseDto ordemSalva = ordemServicoService.salvarOrdem(ordemRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(ordemSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> ordemAtualizar(@PathVariable UUID id,
                                               @RequestBody @Valid OrdemServicoRequestDto ordemRequest) {
        ordemServicoService.atualizarOrdem(id, ordemRequest);
        return ResponseEntity.noContent().build();
    }

}
