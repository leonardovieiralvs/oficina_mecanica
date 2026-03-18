package io.github.lsouza.oficina.validator;

import io.github.lsouza.oficina.enums.StatusOS;
import io.github.lsouza.oficina.exceptions.BusinessException;
import io.github.lsouza.oficina.models.OrdemServico;

import java.time.LocalDate;
public class OrdemServicoValidator {

    /**
     * Valida e aplica a transição de status em uma ordem existente.
     *
     * Regras implementadas:
     * - Só pode FINALIZAR (CONCLUIDA) se estiver EM_ANDAMENTO
     * - Não pode concluir duas vezes (se já está CONCLUIDA -> erro)
     * - Ao concluir, preencher dataConclusao com a data atual
     * - Não pode cancelar se já estiver CONCLUIDA
*/


    public static void validarTransicao(OrdemServico existe, StatusOS novoStatus) {
        if (existe == null) {
            throw new IllegalArgumentException("OrdemServico existente não pode ser nula");
        }

        if (novoStatus == null) {
            return;
        }

        StatusOS atual = existe.getStatus();

        // Regra: CONCLUIR
        if (novoStatus == StatusOS.CONCLUIDA) {
            if (atual == StatusOS.CONCLUIDA) {
                throw new BusinessException("status", "Esta ordem já foi CONCLUIDA");
            }
            if (atual != StatusOS.EM_ANDAMENTO) {
                throw new BusinessException("status", "Só é possível concluir uma ordem EM_ANDAMENTO");
            }
            existe.setStatus(StatusOS.CONCLUIDA);
            existe.setDataConclusao(LocalDate.now());
            return;
        }

        // Regra: CANCELAR
        if (novoStatus == StatusOS.CANCELADA) {
            if (atual == StatusOS.CONCLUIDA) {
                throw new BusinessException("status", "Não é permitido cancelar uma ordem já concluída");
            }

            if (atual == StatusOS.CANCELADA) {
                throw new BusinessException("status", "Esta ordem já está CANCELADA");
            }
            existe.setStatus(StatusOS.CANCELADA);
            return;
        }
        existe.setStatus(novoStatus);
    }
}
