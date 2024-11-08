package com.diel.tarefas.domain.tarefa.validacoes;

import com.diel.tarefas.domain.ValidacaoException;
import com.diel.tarefas.domain.tarefa.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidadorTarefaComposite {

    private final List<ValidadorTarefa> validadores;

    @Autowired
    public ValidadorTarefaComposite(List<ValidadorTarefa> validadores) {
        this.validadores = validadores;
    }

    public void validar(Tarefa tarefa) throws ValidacaoException {
        for (ValidadorTarefa validador : validadores) {
            validador.validar(tarefa);
        }
    }
}
