package io.github.lsouza.oficina.repository.specs;

import io.github.lsouza.oficina.models.Veiculo;
import org.springframework.data.jpa.domain.Specification;

public class VeiculoSpecificationRules {

    public static Specification<Veiculo> placaEquals(String placa) {
        return (root, query, cb) ->  cb.equal(root.get("placa"), placa);
    }

    public Specification<Veiculo> modeloLike(String modelo) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("modelo")), "%" + modelo.toUpperCase() + "%");
    }

    public Specification<Veiculo> anoEquals(Integer ano) {
        return (root, query, cb) -> cb.equal(root.get("ano"), ano);
    }
}
