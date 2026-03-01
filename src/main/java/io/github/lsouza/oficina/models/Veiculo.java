package io.github.lsouza.oficina.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Campo obrigatório")
    private String placa;

    @NotBlank(message = "Campo obrigatório")
    private String modelo;

    @NotBlank(message = "Campo obrigatório")
    private Integer ano;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "veiculo")
    private List<OrdemServico> ordemServico;
}
