package io.github.lsouza.oficina.controller;

import io.github.lsouza.oficina.dto.VeiculoResponseDto;
import io.github.lsouza.oficina.service.VeiculoService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
