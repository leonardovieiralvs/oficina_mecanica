package io.github.lsouza.oficina.repository.specs;

import io.github.lsouza.oficina.enums.StatusOS;
import io.github.lsouza.oficina.models.OrdemServico;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class OrdemSpecificationRules {

    public static Specification<OrdemServico> descricaoLike(String descricao) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("descricao")), "%" + descricao.toUpperCase() + "%");
    }

    public static Specification<OrdemServico> valorMin(BigDecimal valorMin) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("valor"), valorMin);
    }

    public static Specification<OrdemServico> valorMax(BigDecimal valorMax) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("valor"), valorMax);
    }

    public static Specification<OrdemServico> statusEquals(StatusOS status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }
}
