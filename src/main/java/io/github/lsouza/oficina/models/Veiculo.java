package io.github.lsouza.oficina.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotBlank(message = "Campo obrigatório")
    private String placa;
    @NotBlank(message = "Campo obrigatório")
    private String modelo;
    @NotBlank(message = "Campo obrigatório")
    private Integer ano;
    @NotNull(message = "O Cliente precisa ter um veículo")
    private Cliente cliente;
}
