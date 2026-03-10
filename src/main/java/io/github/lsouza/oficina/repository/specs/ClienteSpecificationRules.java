package io.github.lsouza.oficina.repository.specs;

import io.github.lsouza.oficina.models.Cliente;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecificationRules {

    public static Specification<Cliente> nomesLike(String nome) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
    }

    public static Specification<Cliente> cpfEquals(String cpf) {
        return (root, query, cb) -> cb.equal(root.get("cpf"), cpf) ;
    }

    public static Specification<Cliente> telefoneEquals(String telefone) {
        return (root, query, cb) -> cb.equal(root.get("telefone"), telefone);
    }
}
