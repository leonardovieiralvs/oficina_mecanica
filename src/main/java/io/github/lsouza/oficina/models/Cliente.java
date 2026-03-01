package io.github.lsouza.oficina.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    private String cpf;

    @NotBlank(message = "Campo obrigatório")
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Veiculo> veiculo;


}
