package io.github.lsouza.oficina.models;

import io.github.lsouza.oficina.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    private String senha;

    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

}
