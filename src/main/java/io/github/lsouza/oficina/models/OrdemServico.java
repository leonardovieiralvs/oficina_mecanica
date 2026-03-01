package io.github.lsouza.oficina.models;

import io.github.lsouza.oficina.enums.StatusOS;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Campo obrigatório")
    private String descricao;

    @NotNull(message = "Campo obrigatório")
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusOS statusOS;

    @CreatedDate
    private LocalDate dataAbertura;

    @LastModifiedDate
    private LocalDate dataConclusao;

    @ManyToOne
    private Veiculo veiculo;
}
