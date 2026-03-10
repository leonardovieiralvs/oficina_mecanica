package io.github.lsouza.oficina.validator;

import io.github.lsouza.oficina.enums.StatusOS;
import io.github.lsouza.oficina.exceptions.BusinessException;
import io.github.lsouza.oficina.models.OrdemServico;

public class OrdemServicoValidator {

    public static void concluirApenasEmAndamento(OrdemServico ordemServico) {

        if (ordemServico.getStatus() != StatusOS.EM_ANDAMENTO) {
            throw new BusinessException("Status", "Só é possível concluir uma ordem EM_ANDAMENTO");
        }
        ordemServico.setStatus(StatusOS.CONCLUIDA);
    }

    public static void concluirApenasUmaVez(OrdemServico ordemServico) {

        if (ordemServico.getStatus() == StatusOS.CONCLUIDA) {
            throw new BusinessException("Status", "Esta ordem já foi CONCLUIDA");
        }
    }

    public static void naoCancelarAposConcluida(OrdemServico ordemServico) {

        if (StatusOS.CONCLUIDA.equals(ordemServico.getStatus())) {
            throw new BusinessException("Status", "Não é permitido cancelar uma ordem já concluída");
        }
    }
}


/*

Regras:
- Só pode FINALIZAR se estiver EM_ANDAMENTO
- Não pode concluir duas vezes
- Ao concluir, preencher dataConclusao
- Não pode cancelar se já estiver CONCLUIDA

 */