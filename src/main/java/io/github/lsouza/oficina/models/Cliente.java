package io.github.lsouza.oficina.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

//    @NotBlank(message = "Campo obrigatório")
    private String nome;

    @NotBlank(message = "Campo obrigatório")
    private String cpf;

    @NotBlank(message = "Campo obrigatório")
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Veiculo> veiculo;

    public @NotBlank(message = "Campo obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "Campo obrigatório") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "Campo obrigatório") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank(message = "Campo obrigatório") String cpf) {
        this.cpf = cpf;
    }

    public @NotBlank(message = "Campo obrigatório") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank(message = "Campo obrigatório") String telefone) {
        this.telefone = telefone;
    }
}
