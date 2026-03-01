package io.github.lsouza.oficina.models;

import io.github.lsouza.oficina.enums.StatusOS;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@EntityListeners(AuditingEntityListener.class)
@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @NotBlank(message = "Campo obrigatório")
    private String descricao;
    @NotNull(message = "Campo obrigatório")
    private BigDecimal valor;
    @NotNull(message = "Campo obrigatório")
    private StatusOS statusOS;
    @CreatedDate
    private LocalDate dataAbertura;
    @LastModifiedDate
    private LocalDate dataConclusao;
    private Veiculo veiculo;
}
 // -------------
//id: UUID
//descricao: String
//valor: BigDecimal
//status: StatusOS (ENUM)
//dataAbertura: LocalDate
//dataConclusao: LocalDate
//veiculo: Veiculo