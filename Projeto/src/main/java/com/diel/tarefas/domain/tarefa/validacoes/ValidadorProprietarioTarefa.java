package com.diel.tarefas.domain.tarefa.validacoes;

import com.diel.tarefas.domain.ValidacaoException;
import com.diel.tarefas.domain.tarefa.Tarefa;
import com.diel.tarefas.infra.auth.AuthenticationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorProprietarioTarefa implements ValidadorTarefa {

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Override
    public void validar(Tarefa tarefa) throws ValidacaoException {
        if (!tarefa.getUsuario().equals(authenticationHelper.getAuthenticatedUser())) {
            throw new ValidacaoException("O usuário não é o proprietário da tarefa.");
        }
    }
}
